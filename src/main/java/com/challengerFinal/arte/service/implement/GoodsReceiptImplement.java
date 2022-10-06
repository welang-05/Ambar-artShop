package com.challengerFinal.arte.service.implement;

import com.challengerFinal.arte.dtos.GoodsReceiptDto;
import com.challengerFinal.arte.model.*;
import com.challengerFinal.arte.repositories.*;
import com.challengerFinal.arte.service.GoodsReceiptService;
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
public class GoodsReceiptImplement implements GoodsReceiptService {
    @Autowired
    GoodsReceiptRepository goodsReceiptRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PaymentRepository  paymentRepository;

  /*  @Autowired
    MailService mailService;*/
    @Override
    public List<GoodsReceiptDto> getGoodsReceiptsAll() {
        return goodsReceiptRepository.findAll().stream().map(GoodsReceiptDto::new).collect(Collectors.toList());
    }

    @Override
    public GoodsReceipt getGoodsReceiptId(Long id) {
        return goodsReceiptRepository.findById(id).get();
    }

    @Override
    public GoodsReceipt saveGoodsReceipt(GoodsReceipt receipt) {
        return goodsReceiptRepository.save(receipt);
    }

    @Transactional
    @Override
    public ResponseEntity<Object> createGoodsReceipt(int payments,
                                                     String typePayment,
                                                     String numCard,
                                                     LocalDate fechaVen,
                                                     String cvv,
                                                     Authentication authentication) {

        Client clientConected=clientRepository.findByEmail(authentication.getName());
            ShoppingCart shoppingCartNow= shoppingCartRepository.findByClientAndActive(clientConected,true);
            Payment paymentType= paymentRepository.findByName(typePayment);

            if (shoppingCartNow == null){
                return new ResponseEntity<>("No existe el carrito", HttpStatus.FORBIDDEN);
            }

            if (payments==0 || typePayment.isEmpty()) {
                return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
            }

            if (shoppingCartNow.getOrderRequest().isEmpty()){
                return new ResponseEntity<>("No hay ninguna compra", HttpStatus.FORBIDDEN);
            }

            if (paymentType == null){
                return new ResponseEntity<>("No existe el medio de pago", HttpStatus.FORBIDDEN);
            }

            if (numCard.length()!=16){
                return new ResponseEntity<>("El número de la tarjeta no es válido", HttpStatus.FORBIDDEN);
            }

            if (cvv.length()!=3){
                return new ResponseEntity<>("El número de seguridad no es válido", HttpStatus.FORBIDDEN);
            }

            if (fechaVen.isBefore(LocalDate.now())){
                return new ResponseEntity<>("La tarjeta ya expiró", HttpStatus.FORBIDDEN);
            }

            if (!paymentType.getPayments().contains(payments)){
                return new ResponseEntity<>("La cantidad de cuotas no es valida para ese medio de pago", HttpStatus.FORBIDDEN);
            }

            //List<OrderRequest> purchaseOrders= orderRepository.findByShoppingCart(shoppingCartNow);
            List <OrderRequest> orderRequest = orderRepository.findByShoppingCart(shoppingCartNow);
            Double totalPrice = 0.0;

            for (int x = 0 ;x < shoppingCartNow.getOrderRequest().size();x++){
                System.out.println("hola mundo: " + x);
                totalPrice+= orderRequest.get(x).getPrice();
            }

            GoodsReceipt invoice = new GoodsReceipt();
            goodsReceiptRepository.save(invoice);

            shoppingCartNow.setActive(false);
            shoppingCartRepository.save(shoppingCartNow);
            ShoppingCart newShoppingCart = new ShoppingCart(clientConected);
            shoppingCartRepository.save(newShoppingCart);

            String message="MarketSito:"+"\n"
                    +"Tu compra se realizó exitosamente" + "\n"
                    + "N° compra: "+invoice.getId() + "\n"
                    + "Monto total: "+invoice.getTotalPrice() + "\n"
                    + "Tipo de pago: " + invoice.getPayment().getName()
                    + ". En: "+invoice.getPayments()+" cuotas." + "\n"
                    + "Compraste: "+ shoppingCartNow.getOrderRequest().stream()
                    .map(purchaseOrder -> purchaseOrder.getProduct().getName()).collect(Collectors.toSet());

            /*mailService.sendMail("confirm@gmail.com",
                    clientConected.getEmail(),"MarketSito: Compra realizada :)", message);*/

            return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
