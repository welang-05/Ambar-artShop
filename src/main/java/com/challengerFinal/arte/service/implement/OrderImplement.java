package com.challengerFinal.arte.service.implement;

import com.challengerFinal.arte.dtos.AddItemDTO;
import com.challengerFinal.arte.dtos.OrderRequestDto;
import com.challengerFinal.arte.model.*;
import com.challengerFinal.arte.model.enums.StatePedido;
import com.challengerFinal.arte.repositories.*;
import com.challengerFinal.arte.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderImplement implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Override
    public OrderRequestDto getOrderRequest(Long id) {
        return orderRepository.findById(id).map(OrderRequestDto::new).orElse(null);
    }

    @Override
    public List<OrderRequestDto> getOrderRequestsAll() {
        return orderRepository.findAll().stream().map(OrderRequestDto::new).collect(Collectors.toList());
    }

    @Override
    public OrderRequest saveRequest(OrderRequest orderRequest) {
        return orderRepository.save(orderRequest);
    }


    @Override
    public ResponseEntity<Object> createOrder(String nameProduct,
                                              int cant,
                                              Authentication authentication) {

        Client clientConnected=clientRepository.findByEmail(authentication.getName());
        Product addProduct= productRepository.findByName(nameProduct);
        ShoppingCart shoppingCartNow = shoppingCartRepository.findByClient(clientConnected);

        if (shoppingCartNow == null){
            return new ResponseEntity<>("No existe el carrito", HttpStatus.FORBIDDEN);
        }
        if (nameProduct.isEmpty() || cant==0) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (addProduct ==  null) {
            return new ResponseEntity<>("No vendemos ese producto", HttpStatus.FORBIDDEN);
        }
        if (addProduct.getUnits() <  cant) {
            return new ResponseEntity<>("No hay suficiente stock para ese pedido", HttpStatus.FORBIDDEN);
        }

        //Crear Order
        OrderRequest order=new OrderRequest(
                addProduct,
                LocalDate.now(),
                StatePedido.CONFIRMED,
                addProduct.getPrice()*cant,
                cant,
                shoppingCartNow);
        orderRepository.save(order);

        int stock = addProduct.getUnits() - cant;
        addProduct.setUnits(stock);
        productRepository.save(addProduct);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<Object> deleteItem(Long id) {
        OrderRequest toItemDelete = orderRepository.findById(id).orElse(null);
        if (toItemDelete == null) {

            return new ResponseEntity<>(
                    "El item no está en el carrito",
                    HttpStatus.FORBIDDEN);
        }

        orderRepository.deleteById(toItemDelete.getId());

        return new ResponseEntity<>("Item removed from cart", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addItem(Long id, AddItemDTO addItemDTO) {
        OrderRequest itemActualize = orderRepository.findById(id).orElse(null);
        Product product = productRepository.findById(itemActualize.getProduct().getId()).orElse(null);
        double precioActualizado = 0;

        if (addItemDTO.getUnits() != 0){
            itemActualize.setUnits(addItemDTO.getUnits());
            precioActualizado = itemActualize.getProduct().getPrice() * itemActualize.getUnits();
            itemActualize.setPrice(precioActualizado);
            assert product != null;
            product.setUnits(product.getUnits() - itemActualize.getUnits());
        }
        assert product != null;
        productRepository.save(product);
        orderRepository.save(itemActualize);

        return new ResponseEntity<>("Cantidad del item actualizado", HttpStatus.CREATED);
    }


}
