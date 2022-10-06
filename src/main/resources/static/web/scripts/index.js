const app = Vue.createApp({
    data() {
        return {
            tema: '',
        }
    },
    created() {
        this.initialTheme();
    },
    mounted() {

    },
    methods: {
        theme(a) {
            this.tema = `tema_${a}`;
            if(parseInt(a)>6){
                this.tema = 'tema_1';
            }
            if(a.lenght==0){
                this.tema = 'tema_1'
            }
            localStorage.setItem('theme', JSON.stringify(a));
        },
        initialTheme() {
            if (JSON.parse(localStorage.getItem('theme'))) {
                this.theme(JSON.parse(localStorage.getItem('theme')))
            }
        },
    },
    computed: {
    }
}).mount('#app')

/* Para Nacimiento de Venus */
document.querySelectorAll('.venus').forEach(element => {
    const modx = element.getAttribute('data-mod-x');
    const mody = element.getAttribute('data-mod-y');
    const scala = element.getAttribute('scala');

    const venus = basicScroll.create({
        elem: element,
        from: 300,
        to: 3300,
        direct: true,
        props: {
            '--translateX': {
                from: '0',
                to: `${10 * modx}px`
            },
            '--translateY': {
                from: '0',
                to: `${10 * mody}px`
            },
            '--scala': {
                from: '1',
                to: `${1 * scala}`
            }
        }
    }).start();
});

/* Para bienvenida a la página */
var section = document.querySelector('#section');
window.addEventListener('scroll', function () {
    var value = window.scrollY;
    section.style.clipPath = "circle(" + value + "px at center)"
})

/* Para BannerA (Trasladar en Y)*/
document.querySelectorAll('.bannerA').forEach(element => {
    const bannerAy = element.getAttribute('data-mod-y');
    console.log(bannerAy);
    const bannerA = basicScroll.create({
        elem: element,
        from: 'top-middle',
        to: 'bottom-middle',
        direct: true,
        props: {
            '--bannerA-translateY': {
                from: '0',
                to: `${10 * bannerAy}px`
            }
        }
    }).start();
});

/* Para BannerA (Trasladar en X)*/
document.querySelectorAll('.bannerB').forEach(element => {
    const bannerBx = element.getAttribute('data-mod-x');
    console.log(bannerBx);
    const bannerB = basicScroll.create({
        elem: element,
        from: 'top-middle',
        to: 'bottom-middle',
        direct: true,
        props: {
            '--bannerB-translateX': {
                from: '0',
                to: `${10 * bannerBx}px`
            }
        }
    }).start();
});


document.querySelectorAll('.fadeIn').forEach(element=>{
    const fadeIn = basicScroll.create({
        elem: element,
        from: 'bottom-bottom',
        to: 'top-middle',
        direct: true,
        props: {
          '--opacidad': {
            from: .01,
            to: .99
          }
        }
      }).start();
});
