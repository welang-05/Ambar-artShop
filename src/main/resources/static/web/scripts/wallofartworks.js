const { createApp } = Vue
createApp({
  data() {
    return {
      activeModal: false,
      products: [],
      productDetail: [],
      categories: [],
      categoriesFilter: [],
      categoriesSelect: [],
      nombre: "",
      productsFilter:[],
      articuloCarrito:[],
      elementosCarrito:[],
      itemCarrito:{
        id:0,
        stock:0,
        cantidad:0,
        total:0
      },
      cond:false,
      cantidad:0,
      state: false,
                  /* PARA FUNCIONAR EL NAV */
                  tema: '',
                  user: '',
                  modalLoginRegister: false,
                  modalSimple: false,
                  modal: "",
                  goTo: "",
                  profileInfo: "",
                  profileImage: "",
                  modalUploadPhoto: false,
                  changeImage: false,
                  login: {
                      email: "",
                      password: ""
                  },
                  mjeError: "",
                  userType: '',
                  register: {
                      name: '',
                      lastName: '',
                      email: '',
                      password: ''
                  },
                  repeatedPassword: '',
                  termsOfServiceChecked: "",
                  p: "",
                  file2: "",
                  optionChange: true,
      
    }
  },
  created() {
    this.getCurrentClient();
    this.getProducts();
    this.initialTheme();
    console.log(this.user);
    this.currency = this.function;
    this.musica();
  },
  mounted(){
    this.addClassY('navbar', 50, 'glass1', 'A');
  },
  methods: {
    getProducts() {
      axios.get('/api/products')
        .then((response) => {
          this.products = response.data
          this.productsFilter = this.products
          console.log(this.products)
          this.categories = this.products.map(product => product.category)
          for(var i = 0; i < this.categories.length; i++) {
            const elemento = this.categories[i];
            if (!this.categoriesFilter.includes(this.categories[i])) {
              this.categoriesFilter.push(elemento);
            }
          }
          console.log(this.categoriesFilter);
          // this.ej = this.products[0]
          // console.log(this.products[0])
          // this.product1 = this.products.slice(0, 1)
          // this.galleryImage1 = this.product1[0].image
          // /* de los productos, traerme un array con cada nombre de cada productos*/
          // this.arrayDeImagenes = this.products.map((product) => product.image)
          // this.img1 = bg - [url('${this.arrayDeImagenes[0]}')]
          // this.img2 = bg - [url('${this.arrayDeImagenes[1]}')]
          // this.img3 = bg - [url('${this.arrayDeImagenes[2]}')]
          // this.img4 = bg - [url('${this.arrayDeImagenes[3]}')]
          // this.img5 = bg - [url('${this.arrayDeImagenes[4]}')]
          // this.img6 = bg - [url('${this.arrayDeImagenes[5]}')]
        })
        .catch((error) => console.log(error))
    },
    getCurrentClient() {
      axios.get('/api/clients/current')
        .then((response) => console.log(response.data))
    },
    theme(a) {
      this.tema = `tema_${a}`;
      localStorage.setItem('theme', JSON.stringify(a));
    },
    initialTheme() {
      if (JSON.parse(localStorage.getItem('theme'))) {
        this.theme(JSON.parse(localStorage.getItem('theme')))
      }
    },
    addClassY(ref, yTrigger, classToAdd, classToRemove) {
      const element = eval(`this.$refs.${ref}`);
      window.addEventListener("scroll", () => {
        if (window.scrollY > yTrigger) {
          element.classList.add(classToAdd);
          element.classList.remove(classToRemove);
        } else {
          element.classList.remove(classToAdd);
          element.classList.add(classToRemove);
        }
      })
    },
    function(number){
      return new Intl.NumberFormat('en-US', {style: 'currency',currency: 'USD', minimumFractionDigits: 2}).format(number);
    },
    filtrarPorNombre(products){
      this.productsFilter = products.filter(product =>
      (product.name.toLowerCase().includes(this.nombre.toLowerCase()) |  product.artistName.toLowerCase().includes(this.nombre.toLowerCase()) | product.artistLastName.toLowerCase().includes(this.nombre.toLowerCase())) );
    },
    cantidadProducto(id){
      this.articuloCarrito = JSON.parse(localStorage.getItem('articulos'))
      let filter_repeated = this.articuloCarrito.filter(articulo => articulo.id == (id))
      if (filter_repeated.length){
        index = this.articuloCarrito.findIndex(articulo => articulo.id == id)
        this.cantidad = this.articuloCarrito[index].cantidad
      }else{
        this.cantidad = 0
      }
    },
    escuchadorCarrito(producto) {
      console.log(producto);
      console.log(this.cantidad);
      this.articuloCarrito = JSON.parse(localStorage.getItem('articulos'))
      this.itemCarrito.id = producto.id
      this.itemCarrito.stock = producto.units
      this.itemCarrito.total = producto.price
      if (this.articuloCarrito != null) {
        let filter_repeated = this.articuloCarrito.filter(articulo => articulo.id == (producto.id))

        if (filter_repeated.length) {
          this.itemCarrito.id = filter_repeated.id
          this.itemCarrito.stock = filter_repeated.units
          this.itemCarrito.cantidad = filter_repeated.cantidad
          this.itemCarrito.total = filter_repeated.price * filter_repeated.cantidad
          index = this.articuloCarrito.findIndex(articulo => articulo.id == producto.id)

          if (this.articuloCarrito[index].cantidad < this.articuloCarrito[index].stock) {
            this.articuloCarrito[index].cantidad += 1
            this.articuloCarrito[index].total = filter_repeated.price *  this.articuloCarrito[index].cantidad
          }else{
            console.log("no stock");
          }
        } else {
          this.itemCarrito.cantidad = 1
          this.cantidad = 1
          this.articuloCarrito.push(this.itemCarrito)
        }
        console.log(this.articuloCarrito);
        localStorage.setItem('articulos', JSON.stringify(this.articuloCarrito))
      } else {
        this.articuloCarrito = []
        this.itemCarrito.cantidad = 1
        this.articuloCarrito.push(this.itemCarrito)
        this.cantidad = 1
        console.log(this.articuloCarrito);
        localStorage.setItem('articulos', JSON.stringify(this.articuloCarrito))
        console.log(this.cantidad);
      }
      this.cond=true
    },
    sumarArticulo(idArticulo) {
      this.articuloCarrito = JSON.parse(localStorage.getItem('articulos'))
      console.log(this.articuloCarrito);
      index = this.articuloCarrito.findIndex(articulo => articulo.id == idArticulo)
      if ((this.articuloCarrito[index].cantidad + 1) <= this.articuloCarrito[index].stock) {
        let price = (this.articuloCarrito[index].total / this.articuloCarrito[index].cantidad)
        this.articuloCarrito[index].cantidad += 1
        this.articuloCarrito[index].total = price * this.articuloCarrito[index].cantidad
        
        this.cantidad += 1
      } else {
        console.log("no");
      }
      localStorage.setItem('articulos', JSON.stringify(this.articuloCarrito))
    },
    restarArticulo(idArticulo) {
      
      this.articuloCarrito = JSON.parse(localStorage.getItem('articulos'))
      index = this.articuloCarrito.findIndex(articulo => articulo.id == idArticulo)
      if ((this.articuloCarrito[index].cantidad) > 1) {
        let price = (this.articuloCarrito[index].total / this.articuloCarrito[index].cantidad)
        this.articuloCarrito[index].cantidad -= 1
        this.articuloCarrito[index].total = price * this.articuloCarrito[index].cantidad
        this.cantidad -= 1
      } else {
        this.articuloCarrito = this.articuloCarrito.filter(articulo => articulo.id != idArticulo)
        this.cantidad = 0
      }
      localStorage.setItem('articulos', JSON.stringify(this.articuloCarrito))
    },
    musica() {
      setTimeout(function () {
        var vid = document.getElementById('intro')
        vid.play()
        vid.volume = "0.5"
      },200)
    },
    estado() {
      var vid = document.getElementById('intro')
      this.state = vid.paused
      if (this.state) {
        vid.play()
        this.state = vid.paused
        vid.volume = "0.5"
      } else {
        vid.pause()
        this.state = vid.paused
        vid.volume = "0.5"
      }
    },
  },
  computed:{
    buscador(){
      if(this.categoriesSelect.length != 0){
        this.productsFilter = this.products.filter(product =>{ 
        return this.categoriesSelect.includes(product.category)})

      }else{
          this.productsFilter = this.products
      }
      if(this.nombre != ''){
        this.filtrarPorNombre(this.productsFilter)
      }
    },
  }
}).mount('#app')