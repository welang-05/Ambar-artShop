const { createApp } = Vue
createApp({
    data() {
        return {
            file: [],
        }
    },
    created() {

    },
    methods: {
        select_file(event) {
            this.file = event.target.files[0]
            console.log(this.file)
        },
        upload_file_client() {
            let formData = new FormData()
            formData.append("files", this.file)
            axios.post('/api/files/upload/client',
                formData,
                { headers: { 'content-type': 'multipart/form-data' } })
                .then(response => console.log("uploaded"))
                .catch(error => alert(error.response.data))
        },
        upload_file_product() {
            let formData = new FormData()
            let idProduct = 2
            formData.append("files", this.file)
            formData.append("idProduct", idProduct)
            axios.post('/api/files/upload/product',
                formData,
                { headers: { 'content-type': 'multipart/form-data' } })
                .then(response => console.log("uploaded"))
                .catch(error => alert(error.response.data))
        },
        login() {
            axios.post('/api/login', "email=juda2@goto.com&password=654",
                { headers: { 'content-type': 'application/x-www-form-urlencoded' } }).then(response => console.log('signed in!!!'))
                .then(response => console.log("logged in!"))
                .catch(error => alert(error.response.data))
        }
    }
}).mount('#app')