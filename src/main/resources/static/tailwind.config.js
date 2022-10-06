/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './*.html',
    './web/styles/*.css',
    './web/scripts/*.js',
    './web/artist/*.html',
    './web/artlover/*.html',
    './web/artistandartlovers/*.html',
    './web/public/*.html'
  ],
  theme: {
    extend: {
      textColor: {
        skin:{
          base: withOpacity('--color-text-base'),
          inverted: withOpacity('--color-text-inverted'),
          'inverted-accent': withOpacity('--color-text-inverted-accent'),
          muted: withOpacity('--color-text-muted'),
          accent: withOpacity('--color-text-accent')
        }
      },
      backgroundColor:{
        skin:{
          normal: withOpacity('--color-normal'),
          'cuasi-normal': withOpacity('--color-cuasi-normal'),
          base: withOpacity('--color-button-base'),
          accent: withOpacity('--color-button-accent'),
          muted: withOpacity('--color-button-muted'),
          disabled: withOpacity('--color-button-disabled'),
          inverted: withOpacity('--color-text-base')
        }
      },
      gradientColorStops:{
          normal: withOpacity('--color-normal'),
          'cuasi-normal': withOpacity('--color-cuasi-normal'),
          base: withOpacity('--color-button-base'),
          accent: withOpacity('--color-button-accent'),
          muted: withOpacity('--color-button-muted'),
          disabled: withOpacity('--color-button-disabled'),
          inverted: withOpacity('--color-text-base')
      },
      borderColor: {
         skin:{
          normal: withOpacity('--color-normal'),
          base: withOpacity('--color-button-base'),
          accent: withOpacity('--color-button-accent'),
          muted: withOpacity('--color-button-muted'),
          disabled: withOpacity('--color-button-disabled'),
          inverted: withOpacity('--color-text-base')
         }
      },
      boxShadowColor:{
        skin:{
          normal: withOpacity('--color-normal'),
          base: withOpacity('--color-button-base'),
          accent: withOpacity('--color-button-accent'),
          muted: withOpacity('--color-button-muted'),
          disabled: withOpacity('--color-button-disabled'),
          inverted: withOpacity('--color-text-base')
        }
      },
      fontFamily: {
        bahnschrift : ['Bahnschrift','sans-serif'],
        quicksand: ['Quicksand','sans-serif'],
        cinzel: ['Cinzel', 'serif']
      },
      aspectRatio: {
        card1: '8.6/5.4',
        card2: '5.4/8.6'
      },
    },
  },
  plugins: [],
}

function withOpacity(variable){
  return ({opacityValue}) => {
    if (opacityValue != undefined){
      return `rgba(var(${variable}),${opacityValue})`;
    }
    return `rgb(var(${variable}))`;
  }
}