const { createApp } = Vue
createApp({
  data() {
    return {
      state: false,
      tema: '',
      articuloCarrito: [],
      products: [],
      carProducts: [],
      numero: 0,
      marketingProducts: [],
      itemCarrito: {
        id: 0,
        stock: 0,
        cantidad: 0,
        total: 0
      },
      totalPay: 0,
      /* PARA FUNCIONAR EL NAV */

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
      file: "",
      optionChange: true,
    }
  },
  created() {
    this.currency = this.function
    this.getCart();
    this.getProducts();
    this.getCurrentClient();
    this.total();
    this.musica();
    this.initialTheme();
  },
  mounted(){
    this.addClassY('navbar', 60, 'glass1', 'A');
  },
  methods: {
    goToPaymenLink() {
      console.log(this.carProducts)
      let requestBody = []
      this.carProducts.forEach(product => {
        requestBody.push({
          "id": product.id.toString(),
          "quantity": product.cantidad
        })
      })
      axios.post('/api/paymentmp', requestBody,
        { headers: { 'content-type': 'application/json' } })
        .then(response => {
          location.href = response.data
        })
        .catch(error => alert(error.response.data))
      console.log(requestBody)
    },
    getCurrentClient() {
      axios.get('/api/clients/current')
        .then((response) => {
          console.log(response.data)
          this.profileInfo = response.data
          this.profileImage = this.profileInfo.image
          console.log(this.profileImage)
          localStorage.setItem("user", "authenticated")
          this.user = "authenticated"
          console.log(this.user)
        })
        .catch((error) => {
          localStorage.setItem("user", "visitor")
          this.user = "visitor"
          console.log(this.user)
        })
    },
    theme(a) {
      this.tema = `tema_${a}`;
      if (parseInt(a) > 6) {
        this.tema = 'tema_1';
      }
      if (a.lenght == 0) {
        this.tema = 'tema_1'
      }
      localStorage.setItem('theme', JSON.stringify(a));
    },
    initialTheme() {
      if (JSON.parse(localStorage.getItem('theme'))) {
        this.theme(JSON.parse(localStorage.getItem('theme')))
      }
    },
    logout() {
      axios.post('/api/logout').then((response) => this.user = "visitor")
      localStorage.setItem("user", "visitor")
      this.user = localStorage.getItem("user")
    },
    loginAccount(e) {
      e.preventDefault()
      console.log(this.login)
      axios.post(`/api/login?email=${this.login.email}&password=${this.login.password}`)
        .then((response) => {
          this.modalSimple = true
          this.modalLoginRegister = false
          this.getCurrentClient()
          localStorage.setItem("user", "authenticated")
          this.user = localStorage.getItem("user")
        }).catch((error) => {
          this.mjeError = 'Wrong email or password'
          console.log(error)
        })
    },
    createAccount() {
      this.mjeError = ''
      console.log(this.register)
      console.log(this.userType)
      if (this.register.password != this.repeatedPassword) {
        this.mjeError = "The passwords are not the same"
      }
      else if (this.termsOfServiceChecked == false) {
        this.mjeError = "You must agree the Terms of Service"
      }
      else {
        axios.post('/api/clients', {
          "name": this.register.name,
          "lastName": this.register.lastName,
          "password": this.register.password,
          "email": this.register.email,
          "typeUser": this.userType
        })
          .then((response) => {
            this.modal = 'login'
            console.log('registrado')
          })
          .catch((error) => {
            this.mjeError = error.response.data

          })
      }
    },
    tt() {
      console.log(this.activeModal, this.modal, this.goTo)
    },
    select_file(event) {
      this.file = event.target.files[0]
      console.log(this.file)
    },
    editPhoto(e) {
      e.preventDefault();
      let formData = new FormData()
      formData.append("files", this.file)
      axios.post('/api/files/upload/client',
        formData,
        { headers: { 'content-type': 'multipart/form-data' } })
        .then(response => {
          location.reload()
        })
        .catch(error => console.log(error))
    },
    redirect() {

      if (this.goTo == 'Store') { this.location = "/web/public/wallofartworks.html" }
      if (this.goTo == 'Profile') { this.location = "/web/artistandartlovers/myprofile.html" }
      console.log(this.location)

      window.location.href = this.location
    },
    select_file(event) {
      this.file = event.target.files[0]
      console.log(this.file)
    },
    editPhoto(e) {
      e.preventDefault();
      let formData = new FormData()
      formData.append("files", this.file)
      axios.post('/api/files/upload/client',
        formData,
        { headers: { 'content-type': 'multipart/form-data' } })
        .then(response => {
          location.reload()
        })
        .catch(error => alert(error.response.data))
    },
    prueba() {
      numero = parseInt(this.tema.slice(-1)) + 1
      if (numero >= 7) {
        this.tema = "tema1"
        numero = 1
      } else {
        this.tema = `tema${numero}`
      }
      localStorage.setItem('theme', JSON.stringify(numero));
      this.initialTheme();
    },
    /* PARA QUE FUNCIONE EL NAV */

    function(number) {
      return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', minimumFractionDigits: 2 }).format(number);
    },
    getCart() {
      this.articuloCarrito = JSON.parse(localStorage.getItem('articulos'))
      console.log(this.articuloCarrito);
    },
    getProducts() {
      axios.get("/api/products")
        .then(response => {
          this.products = response.data
          this.numero = this.products.length
          this.marketing()
          let indices = this.articuloCarrito.map(articulo => articulo.id)
          let cantidades = this.articuloCarrito.map(articulo => articulo.cantidad)
          for (let i = 0; i < indices.length; i++) {
            let indice = indices[i]
            let cantidad = cantidades[i]
            console.log(indice);
            for (let j = 0; j < this.products.length; j++) {
              if (this.products[j].id == indice) {
                this.products[j].cantidad = cantidad
                this.carProducts.push(this.products[j])
              }
            }
          }
        }).catch(error => {
          console.log(error);
        })
      console.log(this.carProducts);

    },
    sumarArticulo(idArticulo) {
      this.articuloCarrito = JSON.parse(localStorage.getItem('articulos'))
      console.log(this.articuloCarrito);
      index = this.articuloCarrito.findIndex(articulo => articulo.id == idArticulo)
      if ((this.articuloCarrito[index].cantidad + 1) <= this.articuloCarrito[index].stock) {
        let price = (this.articuloCarrito[index].total / this.articuloCarrito[index].cantidad)
        this.articuloCarrito[index].cantidad += 1
        this.carProducts[index].cantidad = this.articuloCarrito[index].cantidad
        this.articuloCarrito[index].total = price * this.articuloCarrito[index].cantidad
      } else {
        console.log("no");
      }
      localStorage.setItem('articulos', JSON.stringify(this.articuloCarrito))
      this.total()
    },
    restarArticulo(idArticulo) {

      this.articuloCarrito = JSON.parse(localStorage.getItem('articulos'))
      index = this.articuloCarrito.findIndex(articulo => articulo.id == idArticulo)
      if ((this.articuloCarrito[index].cantidad) > 1) {
        let price = (this.articuloCarrito[index].total / this.articuloCarrito[index].cantidad)
        this.articuloCarrito[index].cantidad -= 1
        this.carProducts[index].cantidad = this.articuloCarrito[index].cantidad
        this.articuloCarrito[index].total = price * this.articuloCarrito[index].cantidad
      } else {
        this.articuloCarrito = this.articuloCarrito.filter(articulo => articulo.id != idArticulo)
        this.carProducts[index].cantidad = 0
        this.carProducts = this.carProducts.filter(product => product != this.carProducts[index])
      }
      localStorage.setItem('articulos', JSON.stringify(this.articuloCarrito))
      this.total()
    },
    eliminarArticulo(idArticulo) {
      this.articuloCarrito = JSON.parse(localStorage.getItem('articulos'))
      index = this.articuloCarrito.findIndex(articulo => articulo.id == idArticulo)
      this.articuloCarrito = this.articuloCarrito.filter(articulo => articulo.id != idArticulo)
      this.carProducts[index].cantidad = 0
      this.carProducts = this.carProducts.filter(product => product != this.carProducts[index])
      localStorage.setItem('articulos', JSON.stringify(this.articuloCarrito))
      this.total()
    },
    marketing() {
      function getRandomInt(max) {
        return Math.floor(Math.random() * max);
      }
      let numeros = []
      var cantidadNumeros = 4;
      while (numeros.length < cantidadNumeros) {
        var numeroAleatorio = Math.ceil(getRandomInt(this.numero));
        var existe = false;
        for (var i = 0; i < numeros.length; i++) {
          if (numeros[i] == numeroAleatorio) {
            existe = true;
            break;
          }
        }
        if (!existe) {
          numeros[numeros.length] = numeroAleatorio;
        }

      }

      console.log(numeros);
      for (let i = 0; i < numeros.length; i++) {
        this.marketingProducts.push(this.products[numeros[i]])
      }
      console.log(this.marketingProducts);
    },
    escuchadorCarrito(producto) {
      console.log(producto);
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
            this.articuloCarrito[index].total = filter_repeated.price * this.articuloCarrito[index].cantidad
          } else {
            console.log("no stock");
          }
        } else {
          this.itemCarrito.cantidad = 1
          this.articuloCarrito.push(this.itemCarrito)
        }
        console.log(this.articuloCarrito);
        localStorage.setItem('articulos', JSON.stringify(this.articuloCarrito))
      } else {
        this.articuloCarrito = []
        this.articuloCarrito.push(this.itemCarrito)
        this.cantidad = 1
        console.log(this.articuloCarrito);
        localStorage.setItem('articulos', JSON.stringify(this.articuloCarrito))
        console.log(this.cantidad);
      }
      this.cond = true
      location.reload()
    },
    total() {
      this.totalPay = 0
      this.articuloCarrito = JSON.parse(localStorage.getItem('articulos'))
      for (let i = 0; i < this.articuloCarrito.length; i++) {
        this.totalPay += this.articuloCarrito[i].total
      }
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
  }


  }
}).mount('#app')