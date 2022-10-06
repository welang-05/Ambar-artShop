

const app = Vue.createApp({
    data() {
        return {
            artist: [],
            products: [],
            activeModal: false,
            activeModalUpdate: false,
            formularioProduct: false,
            formulario: false,
            formEdit:{
                name: "",
                description: "",
                price: 0,
                category: "",
                image: [],
                height: 0,
                width: 0,
                large: 0,
                units: 0,
            },
            formLoad:{
                name: "",
                description: "",
                price: 0,
                category: "",
                image: [],
                height: 0,
                width: 0,
                large: 0,
                units: 0,
            },
            Product: {},
            file:[],
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
        this.initialTheme();
        this.loadData();
        this.getCurrentClient();
        this.musica();
    },
    mounted() {
        this.vueScrollNav();
    },
    methods: {
        vueScrollNav() {
            const navbar = this.$refs.navbar;
            window.addEventListener("scroll", () => {
                if (window.scrollY > 50) {
                    navbar.classList.add("glass1");
                } else {
                    navbar.classList.remove("glass1");
                }
            })
        },
        loadData(){
            const urlParams = new URLSearchParams(window.location.search);
            const Id = urlParams.get('id');
            axios.get(`/api/clients/${Id}`)
            .then(response => {
                this.artist = response.data
                console.log(this.artist)
                this.products = this.artist.products
                console.log(this.products);
            })
        },
        updateProduct(product){
            console.log(product);
            axios.patch(`/api/clients/current/products/update/${product.id}`,{
                "name": this.formEdit.name,
                "description" : this.formEdit.description,
                "category" : this.formEdit.category,
                "dimensionsList" : [this.formEdit.width,this.formEdit.large,this.formEdit.height],
                "image": "",
                "price" : this.formEdit.price,
                "status": true,
                "units" : this.formEdit.units
            })
            .then(response => {
                console.log("ok");
                this.fileUpdate(product)
            }).catch(error => {
                console.log(error);
            })
        },
        loadProduct(){
            console.log(this.formLoad.name);
            console.log(this.formLoad.description);
            console.log(this.formLoad.category);
            console.log(this.formLoad.large);
            console.log(this.formLoad.width);
            console.log(this.formLoad.height);
            console.log(this.formLoad.price);
            console.log(this.formLoad.units);
            
            axios.post("/api/clients/current/products",{
                "name": this.formLoad.name,
                "description" : this.formLoad.description,
                "category" : this.formLoad.category,
                "dimensionsList" : [this.formLoad.large,this.formLoad.width,this.formLoad.height],
                "image": "",
                "price" : this.formLoad.price,
                "status": true,
                "units" : this.formLoad.units
            }).then(response => {
                let productUpdate = []
                const urlParams = new URLSearchParams(window.location.search);
                const Id = urlParams.get('id');
                axios.get(`/api/clients/${Id}`)
                .then(response => {
                    this.artist = response.data
                    console.log(this.artist)
                    this.products = this.artist.products
                    console.log(this.products);
                    productUpdate = this.products.filter(product => product.name == this.formLoad.name)
                    console.log(productUpdate[0]);
                    this.fileUpdate(productUpdate[0])
                })
                
            }).catch(error => {
                console.log(error);
            })
        },
        fileUpdate(product){
            let formData = new FormData()
            let idProduct = product.id
            formData.append("files", this.file)
            formData.append("idProduct", idProduct)
            axios.post('/api/files/upload/product',
                formData,
                { headers: { 'content-type': 'multipart/form-data' } })
                .then(response => {
                    location.reload()
                })
                .catch(error => alert(error.response.data))
        },
        select_file(event) {
            this.file = event.target.files[0]
            console.log(this.file)
        },
        /* PARA QUE FUNCIONE EL NAV */
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
        select_file2(event) {
            this.file2 = event.target.files[0]
            console.log(this.file2)
        },
        editPhoto(e) {
            e.preventDefault();
            let formData = new FormData()
            formData.append("files", this.file2)
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
    computed: {
    }
}).mount('#app')