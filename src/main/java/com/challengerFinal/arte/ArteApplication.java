package com.challengerFinal.arte;

import com.challengerFinal.arte.model.*;
import com.challengerFinal.arte.model.enums.StatePedido;
import com.challengerFinal.arte.model.enums.TypeUser;
import com.challengerFinal.arte.service.PaymentServiceMP;
import com.challengerFinal.arte.dtos.ItemDTO;
import com.challengerFinal.arte.repositories.ClientRepository;
import com.challengerFinal.arte.repositories.PaymentRepository;
import com.challengerFinal.arte.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ArteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ArteApplication.class, args);
		System.out.println("Welcome to ArteApplication for Rafael");
	}
	@Autowired
	ClientRepository userGlobalRepository;

	@Autowired
	ServiceProduct artworksService;

	@Autowired
	OrderService orderService;
	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	GoodsReceiptService goodsReceiptService;
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	ClientRepository compradorRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	PaymentServiceMP paymentServiceMP;

	@Override
	public void run(String... args) throws Exception {
		List<String> socialNetwords = List.of("Twtter","Instagram","Tick Tock");

		Client client = new Client("Juan","Da vinci","juda1@goto.com",passwordEncoder.encode("654"),TypeUser.CLIENT,0,"https://media.istockphoto.com/photos/portrait-of-young-dark-haired-concentrated-caucasian-white-male-with-picture-id1333064056?k=20&m=1333064056&s=612x612&w=0&h=WIBoskVGacXKcFocqymTxwzntfZx2PieiJR3Zptes8w=");

		Client artist = new Client("Juan","Da vinci","don Bot","juda2@goto.com","3124523424",passwordEncoder.encode("654"),TypeUser.ARTIST,"cerca de la casa",0,"https://media.istockphoto.com/photos/portrait-of-young-dark-haired-concentrated-caucasian-white-male-with-picture-id1333064056?k=20&m=1333064056&s=612x612&w=0&h=WIBoskVGacXKcFocqymTxwzntfZx2PieiJR3Zptes8w=",socialNetwords);
		Client admin = new Client("Juan","Da vinci","juda3@goto.com",passwordEncoder.encode("654"),TypeUser.ADMIN,0,"https://media.istockphoto.com/photos/portrait-of-young-dark-haired-concentrated-caucasian-white-male-with-picture-id1333064056?k=20&m=1333064056&s=612x612&w=0&h=WIBoskVGacXKcFocqymTxwzntfZx2PieiJR3Zptes8w=");


		userGlobalRepository.save(client);
		userGlobalRepository.save(artist);
		userGlobalRepository.save(admin);
		System.out.println(client);
		System.out.println(artist);
		System.out.println(admin);

		List<Double> dimensions = List.of(12.5,24.6,36.5);
		String image = "image/jpeg";


		ShoppingCart shoppingCart = new ShoppingCart(client);
		ShoppingCart shoppingCartDos = new ShoppingCart(client);
		ShoppingCart shoppingCartTres = new ShoppingCart(client);
		ShoppingCart shoppingCartCuster = new ShoppingCart(client);
		shoppingCartService.saveShoppingCard(shoppingCart);
		shoppingCartService.saveShoppingCard(shoppingCartDos);
		shoppingCartService.saveShoppingCard(shoppingCartTres);
		shoppingCartService.saveShoppingCard(shoppingCartCuster);
		System.out.println(shoppingCart);
		System.out.println(shoppingCartDos);
		System.out.println(shoppingCartTres);
		System.out.println(shoppingCartCuster);
		//CLAU:
		Client artist1 = new Client("Rosalia", "Ferrando", "roasliaferrando@art.com", passwordEncoder.encode("00"), TypeUser.ARTIST, 1, "https://media.istockphoto.com/photos/selfemployed-painter-looking-at-the-camera-cheerfully-picture-id1364209752?k=20&m=1364209752&s=612x612&w=0&h=yH-XL4ga0E7ixxn-KHexzIC5wWoBdTAX-q6NsHLOOcA=");
		Client artist2= new Client("Francisco José", "Manzanares", "franciscojose@art.com", passwordEncoder.encode("00"), TypeUser.ARTIST, 1, "https://media.istockphoto.com/photos/portrait-of-young-dark-haired-concentrated-caucasian-white-male-with-picture-id1333064056?k=20&m=1333064056&s=612x612&w=0&h=WIBoskVGacXKcFocqymTxwzntfZx2PieiJR3Zptes8w=");
		Client artist3= new Client("María Lucía", "Encinas", "marialucia@art.com", passwordEncoder.encode("00"), TypeUser.ARTIST, 1,  "https://media.istockphoto.com/photos/smiling-young-woman-female-artist-in-apron-looking-at-camera-standing-picture-id1327671241?k=20&m=1327671241&s=612x612&w=0&h=xDAfS2QuFeGQG_F9d671q03TLXhNtKoDR962VETfOao=");
		Client artist4= new Client("Triana", "Ribes", "trianaribes@art.com", passwordEncoder.encode("00"), TypeUser.ARTIST, 5 , "https://media.istockphoto.com/photos/beautiful-afro-woman-with-pigtails-and-stylish-clothes-picture-id1342849839?k=20&m=1342849839&s=612x612&w=0&h=kzIoHr71R1RG4oTrCUc6FqNkf8fF6_Tx1KlWJGAFV1c=");
		Client artist5= new Client("Avelino", "Torre", "avelinotorre@art.com", passwordEncoder.encode("00"), TypeUser.ARTIST, 1 , "https://media.istockphoto.com/photos/working-on-creative-ideas-at-night-picture-id1356061017?k=20&m=1356061017&s=612x612&w=0&h=PoVD5yO_5fpPMxsvTkXeS83LLykkzecO2NKFh1OSsVU=");
		Client artist6= new Client("Adelina", "Barroso", "adelinabarroso@art.com", passwordEncoder.encode("00"), TypeUser.ARTIST, 1, "https://media.istockphoto.com/photos/ceramic-workshop-picture-id1126452727?k=20&m=1126452727&s=612x612&w=0&h=puqfmxVU_61aohW5XxB05kUYI_p1gP8bkw01nanlW9g=");
		Client artist7= new Client("Mar", "Beltran", "marbeltran@art.com", passwordEncoder.encode("00"), TypeUser.ARTIST, 1, "https://media.istockphoto.com/photos/young-professional-female-singer-recording-a-new-song-album-inside-picture-id1319579584?k=20&m=1319579584&s=612x612&w=0&h=wEVsZ-9SXhRX7SFgnvSgx1t7DfzuwRzHLhaTKZYKWxw=");
		Client artist8= new Client("Mariano", "Mayor", "marianomayor@art.com", passwordEncoder.encode("00"), TypeUser.ARTIST, 7, "https://media.istockphoto.com/photos/talented-male-artist-energetically-and-violently-using-paint-brush-he-picture-id1183183769?k=20&m=1183183769&s=612x612&w=0&h=2XU8_xIH02_6sfTQjusOoJGbBN-_hkC8WU4HSrrrblU=");
		Client artist9= new Client("Julio", "Romo", "julioromo@art.com", passwordEncoder.encode("00"), TypeUser.ARTIST, 10, "https://media.istockphoto.com/photos/young-african-musician-smiling-at-camera-picture-id1200779750?k=20&m=1200779750&s=612x612&w=0&h=lE3pHr2wAotZ3n02myTdy0UKMrcw8ar_q8aZR0ohVE8=");
		userGlobalRepository.save(artist1);
		userGlobalRepository.save(artist2);
		userGlobalRepository.save(artist3);
		userGlobalRepository.save(artist4);
		userGlobalRepository.save(artist5);
		userGlobalRepository.save(artist6);
		userGlobalRepository.save(artist7);
		userGlobalRepository.save(artist8);
		userGlobalRepository.save(artist9);




		//CLAU PRODUCTOS
		Product art1= new Product("Angel in Fesccoes", "A painting of angels in heaven made by one of the most renowned artists up to that time. The texture of us canvas creates an unforgettable experience", "paint", 200000.00, true, LocalDate.now(), 2, List.of(20.00, 40.00), "https://media.istockphoto.com/photos/angel-in-frescoes-in-the-dome-of-brunelleschi-picture-id502278435?k=20&m=502278435&s=612x612&w=0&h=JZTuiLZ5RKPS5eZbK8f6IHxTTBVB-d4OMsFbYPuXsxU=", artist1);
		Product art2= new Product("Adam Picture", "The Creation of Adam is a fresco on the vault of the Sistine Chapel, painted by Michael... the Tuscan artist, in which God gives life to Adam, the first man.", "paint", 56000.00, true, LocalDate.now(), 6, List.of(20.00, 40.00), "https://media.istockphoto.com/photos/adam-picture-id92879541?k=20&m=92879541&s=612x612&w=0&h=Q-Lfu2NI1dwrROrmXkYzke66tVTaWrwMbHBEgJZeJVg=", artist1);
		Product art3= new Product("Ergo Paint", "Digital painting using different types of brushes representing different textures, vibrant colors and strangely harmonic expression", "digital art", 270000.00, true, LocalDate.now(), 1, List.of(), "https://media.istockphoto.com/photos/modern-conceptual-artwork-with-ancient-statue-contemporary-art-picture-id1316447596?k=20&m=1316447596&s=612x612&w=0&h=YQ2SyTk8S8x7ezsdgzpNoWvrRSLZy0V7-q1pO11A20o=", artist2);
		Product art4= new Product("Dante and the divine comedy", "The Divine Comedy, also known simply as Comedy, is a poem written by Dante Alighieri. and this is the illustration", "paint", 800.00, true, LocalDate.now(), 15, List.of(200.00, 100.00), "https://media.istockphoto.com/photos/dante-and-the-divine-comedy-in-duomo-picture-id152941916?k=20&m=152941916&s=612x612&w=0&h=C9jNvVjYgKegrMegZO2a7DJP5ibcfF_iH5hzk3PsPH8=", artist1);
		Product art12= new Product("Seville the fresco resurrected", "Representation of the resurrection of Jesus starring him and other characters in the clouds of heaven.", "paint", 7900.00, true, LocalDate.now(), 13, List.of(80.00, 80.00), "https://media.istockphoto.com/illustrations/seville-the-fresco-resurrected-christ-in-venerables-sacerdotes-illustration-id471617800?k=20&m=471617800&s=612x612&w=0&h=yf4aZ8cbw9oHGHqTNR_Sb57gZo41yHajGfPoHJVGAdU=", artist1);
		Product art11= new Product("Bergamo Coronation", "The coronation of Bergamo in the sky.The texture of us canvas creates an unforgettable experience", "paint", 89600.00, true, LocalDate.now(), 4, List.of(60.00, 120.00), "https://media.istockphoto.com/illustrations/bergamo-coronation-of-hl-mary-in-church-san-alessandro-illustration-id177767071?k=20&m=177767071&s=612x612&w=0&h=_jWlAucQjXKE_IWV1FGx9uJaz4LoAGiLIcZebjlx4aU=", artist1);
		Product art7= new Product("Ansient Sculpture", "Ancient sculpture of the rape of the sabine women florence italy picture", "sculpture", 900050.00, true, LocalDate.now(), 1, List.of(150.00, 60.00), "https://media.istockphoto.com/photos/ancient-sculpture-of-the-rape-of-the-sabine-women-florence-italy-picture-id694142262?k=20&m=694142262&s=612x612&w=0&h=SPLIz8PjdNZfb1hGLXeovap7k1acgMjj5_Meb6SOpZI=", artist2);
		Product art8= new Product("Five Flowers", "Bouquet of five flowers of blossoming dandelions of unusual colorful picture", "digital art", 6000.00, true, LocalDate.now(), 4, List.of(), "https://media.istockphoto.com/photos/bouquet-of-five-flowers-of-blossoming-dandelions-of-unusual-colorful-picture-id1223668190?k=20&m=1223668190&s=612x612&w=0&h=JQjlgR0c0rd5VTfk41I2Gr6MiSyj2uuq-8PJ_Uf-OTs=", artist2);
		Product art9= new Product("Beautiful Angel", "The angel of the fallen sunsets, representing the sadness for the death of the protagonist's family, in detail you can see even the tears arising", "sculpture", 4000.00, true, LocalDate.now(), 2, List.of(200.00, 40.00), "https://media.istockphoto.com/photos/beautiful-angel-picture-id155279861?k=20&m=155279861&s=612x612&w=0&h=DHgm3XLQjz5aEa6F_MkWTxGSqyG8ycNV4qjWctSysks=", artist2);
		Product art10= new Product("Robotic Coronation", "Metal sculpture, a robot with a crown of polished spikes. Created 3000 years ago and cared for until then by the descendants of the creator.", "sculpture", 630.00, true, LocalDate.now(), 3, List.of(70.00, 80.00), "https://i.pinimg.com/originals/a9/90/5e/a9905eded97106bd2300dd873a213f48.jpg", artist2);
		Product art6= new Product("Michelangelo Spirit", "Marble sculpture by Michelangelo David. Exported from the United States.", "sculpture", 8000.00, true, LocalDate.now(), 3, List.of(300.00, 40.00), "https://i.pinimg.com/originals/f8/49/bf/f849bfd7cc2da33e949a1f5cd9db71a6.png", artist2);
		Product art5= new Product("Venus", "Imagine Lion Studio Venus resin statue in stock gold version collection", "sculpture", 6000000.00, true, LocalDate.now(), 1, List.of(40.00, 40.00), "https://i.pinimg.com/736x/a8/36/fd/a836fdf1ba811157586054afffff3074.jpg", artist2);

		artworksService.saveArtworks(art1);
		artworksService.saveArtworks(art2);
		artworksService.saveArtworks(art3);
		artworksService.saveArtworks(art4);
		artworksService.saveArtworks(art5);
		artworksService.saveArtworks(art6);
		artworksService.saveArtworks(art7);
		artworksService.saveArtworks(art8);
		artworksService.saveArtworks(art9);
		artworksService.saveArtworks(art10);
		artworksService.saveArtworks(art11);
		artworksService.saveArtworks(art12);

		OrderRequest orderRequest = new OrderRequest(art1,LocalDate.now(), StatePedido.CONFIRMED,234.4,12,shoppingCart);
		OrderRequest orderRequestDos = new OrderRequest(art1,LocalDate.now(), StatePedido.CONFIRMED,25343.3,12,shoppingCart);
		orderService.saveRequest(orderRequest);
		orderService.saveRequest(orderRequestDos);
		System.out.println(orderRequest);
		System.out.println(orderRequestDos);

		List<Integer> paymentsDebitCard = List.of(1);
		List<Integer> paymentsCreditCard = List.of(1, 3, 6,12,24,36);
		List<Integer> paymentsCash = List.of(1);

		Payment payment1 = new Payment("Cash",paymentsCash);
		Payment payment2 = new Payment("Credit",paymentsCreditCard);
		Payment payment3 = new Payment("Debit",paymentsDebitCard);

		paymentRepository.save(payment1);
		paymentRepository.save(payment2);
		paymentRepository.save(payment3);

		GoodsReceipt goodsReceiptOne = new GoodsReceipt((orderRequest.getPrice()*1.010)*12,true,LocalDate.now(),12,shoppingCart,payment1);
		goodsReceiptService.saveGoodsReceipt(goodsReceiptOne);
		System.out.println(goodsReceiptOne);
//CLAU:
		Client artist10= new Client("HEATHER", "ROONEY", "heatherrooney@art.com", passwordEncoder.encode("00"), TypeUser.ARTIST, 20, "https://i.pinimg.com/564x/0a/62/32/0a623223087d848e472f4e7a14b2a45c.jpg");
		userGlobalRepository.save(artist10);
		Client artist11= new Client("YIFAN", "ZHANG", "yiganzhang@art.com", passwordEncoder.encode("00"), TypeUser.ARTIST, 21, "https://i.pinimg.com/564x/46/cb/38/46cb3853959404f3c28189f477169751.jpg");
		userGlobalRepository.save(artist11);
		Client artist12= new Client("TOKIRA", "YAGAMOTO", "tokira@art.com", passwordEncoder.encode("00"), TypeUser.ARTIST, 22, "https://i.pinimg.com/564x/54/f6/e5/54f6e58e763cf6b5bdb4d1da4d6bc621.jpg");
		userGlobalRepository.save(artist12);



		Product art20= new Product("Michael Jackson", "handmade portrait with 2,4,6,8B pencils of the artist michael jackson, on high density paper", "draw", 10000.00, true, LocalDate.now(), 1, List.of(20.00, 40.00), "https://i.pinimg.com/564x/ec/a5/1d/eca51d40d6e0d974444f0499b41d59f1.jpg", artist10);
		Product art21= new Product("Wild Lion", "handmade portrait with 2,4,6,8B pencils of wild lion in color, on high density paper", "draw", 3500.00, true, LocalDate.now(), 1, List.of(50.00, 30.00), "https://i.pinimg.com/originals/bb/20/9d/bb209d4affc02f195f1e5e369ebaca2c.jpg", artist10);
		Product art22= new Product("The greatest comedians", "handmade portrait with 2,4,6,8B pencils of comedians in black and white, on high density paper", "draw", 1500.00, true, LocalDate.now(), 1, List.of(60.00, 10.00), "https://pbs.twimg.com/media/E3S4jFZWYAMhDsz.jpg:large", artist10);
		Product art23= new Product("Rihanna", "handmade portrait with prismacolor colored pencils of the singer and songwriter Rihanna, on high density paper", "draw", 800.00, true, LocalDate.now(), 1, List.of(200.00, 100.00), "https://i.pinimg.com/originals/e7/7a/f2/e77af2a953963836e492454a782a8d65.jpg", artist10);
		Product art24= new Product("Leonardo DiCaprio", "handmade portrait with prismacolor colored pencils of the actor and producer Leonardo DiCaprio, on high density paper", "draw", 7900.00, true, LocalDate.now(), 1, List.of(50.00, 90.00), "https://i.pinimg.com/736x/78/7c/66/787c663ec479b4539b42a7cc704e488b.jpg", artist10);
		artworksService.saveArtworks(art20);
		artworksService.saveArtworks(art21);
		artworksService.saveArtworks(art22);
		artworksService.saveArtworks(art23);
		artworksService.saveArtworks(art24);

		Product art25= new Product("Medici", "a colorful painting mixing Renaissance and contemporary art in oil on extra-large canvas", "paint", 10000.00, true, LocalDate.now(), 1, List.of(210.00, 50.00), "https://i.pinimg.com/564x/24/7d/55/247d557a02970f5a6914c775f0c7b972.jpg", artist11);
		Product art26= new Product("Blue Line", "I thought my life was a tragedy, but now I know it is a comedy", "paint", 1200.00, true, LocalDate.now(), 1, List.of(20.00, 40.00), "https://i.pinimg.com/564x/53/77/de/5377de0a45b1ec3117326ccb4e36da13.jpg", artist11);
		Product art27= new Product("Apolo", "the exemplification of beauty with modern touches in a medium oil on canvas", "paint", 34000.00, true, LocalDate.now(), 1, List.of(30.00, 30.00), "https://i.pinimg.com/564x/3c/71/1b/3c711becd64bea9663bafcc089d994fd.jpg", artist11);
		Product art28= new Product("I don't Care", "make your own assumptions xd", "paint", 5600.00, true, LocalDate.now(), 1, List.of(40.00, 20.00), "https://i.pinimg.com/564x/03/9e/1e/039e1e33c6b30bc6c22a6a210a16fcc8.jpg", artist11);
		Product art29= new Product("Marilyn", "the beauty of this world icon together with the beauty of a historical icon of art", "paint", 12300.00, true, LocalDate.now(), 1, List.of(50.00, 10.00), "https://i.pinimg.com/564x/8e/e1/1e/8ee11e8fa0a2e82b41cadacce445853d.jpg", artist11);
		artworksService.saveArtworks(art25);
		artworksService.saveArtworks(art26);
		artworksService.saveArtworks(art27);
		artworksService.saveArtworks(art28);
		artworksService.saveArtworks(art29);

		Product art30= new Product("Kanae", "male character smoking and playing guitar", "manga", 40.00, true, LocalDate.now(), 3, List.of(21.00, 34.00), "https://i.pinimg.com/564x/5f/3b/05/5f3b058d3411f37e7aa6b1f9fcc41e74.jpg", artist12);
		Product art31= new Product("Nous", "bloodied male character from the famous anime tokyo ghoul", "manga", 120.00, true, LocalDate.now(), 4, List.of(120.00, 23.40), "https://i.pinimg.com/564x/ba/c9/aa/bac9aa56721beef3a87673fe832ca9ee.jpg", artist12);
		Product art32= new Product("Aestethic Girl", "Give this gesture a name that could well mean many things", "manga", 300.00, true, LocalDate.now(), 1, List.of(23.00, 34.80), "https://i.pinimg.com/564x/d4/28/0d/d4280d078d43001a8ffcdd5833204ce5.jpg", artist12);
		Product art33= new Product("Portgas D. Ace", "the famous brother of luffy and son of the pirate king in the one piece series", "manga", 230.00, true, LocalDate.now(), 6, List.of(50.00, 12.40), "https://i.pinimg.com/564x/69/ce/03/69ce03d90e4046030f38f5edefeb4f94.jpg", artist12);
		Product art34= new Product("Eren Jaeger", "Hero or villain?, we still don't know", "manga", 56.00, true, LocalDate.now(), 9, List.of(80.00, 56.80), "https://i.pinimg.com/564x/17/b0/f2/17b0f2003e2c5e6c4f78cfe78785486b.jpg", artist12);
		artworksService.saveArtworks(art30);
		artworksService.saveArtworks(art31);
		artworksService.saveArtworks(art32);
		artworksService.saveArtworks(art33);
		artworksService.saveArtworks(art34);


		////////
		ItemDTO item1 = new ItemDTO("1", "Prueba Pago MindHub", "COP", "", "Prueba compra", "art", 1, 30000.0);
		ItemDTO item2 = new ItemDTO("2", "Prueba Pago MindHub", "COP", "", "Prueba compra", "art", 2, 32000.0);
		List<ItemDTO> items = Arrays.asList(item1,item2);


//
//

//
//		Preference preference = new Preference(items);
//		ObjectMapper mapper = new ObjectMapper();
//		String requestBody = mapper.writeValueAsString(preference);
//		System.out.println(requestBody);
//		String urlAPI = "https://api.mercadopago.com/checkout/preferences";
//		String linkResponse = PaymentUtils.postGetMPUrl(requestBody);
//		System.out.println(linkResponse);
	}
}