 /** @type {import('tailwindcss').Config} */
module.exports = {
  safelist: [
    {
      pattern: /./,
      variants: ['md','hover'], // you can add your variants here
    },
  ],
  theme: {
    screens: {

      'md': '768px',
      // => @media (min-width: 768px) { ... }


    },
  }
}
