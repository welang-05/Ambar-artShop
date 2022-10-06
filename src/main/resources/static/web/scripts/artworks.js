const { createApp } = Vue
createApp({
  data() {
    return {
      user : 'visitor',
      tema : ''
    }
  },
  created(){
    this.getCurrentClient();
    console.log(this.user);
  },
  methods :{
    getCurrentClient(){
    axios.get('/api/clients/current')
                .then((response) => console.log(response.data )) 
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
  }
}).mount('#app')