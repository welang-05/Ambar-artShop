

const app = Vue.createApp({
    data() {
        return {
            tema: '',
            prueba: [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],
            user : 'visitor'
        }
    },
    created() {
        this.initialTheme();
        this.getCurrentClient();
    },
    mounted() {
        this.addClassY('navbar',40,'glass1');
    },
    methods: {
        theme(a) {
            this.tema = `tema_${a}`;
            localStorage.setItem('theme', JSON.stringify(a));
        },
        initialTheme() {
            if (JSON.parse(localStorage.getItem('theme'))) {
                this.theme(JSON.parse(localStorage.getItem('theme')))
            }
        },
        addClassY(ref,yTrigger,classToAdd) {
            const navbar = eval(`this.$refs.${ref}`);
            window.addEventListener("scroll", () => {
                if (window.scrollY > yTrigger) {
                    navbar.classList.add(classToAdd);
                } else {
                    navbar.classList.remove(classToAdd);
                }
            })
        },
        getCurrentClient(){
            axios.get('/api/clients/current')
                        .then((response) => console.log(response.data )) 
                    },
    },
    computed: {
    }
}).mount('#app')