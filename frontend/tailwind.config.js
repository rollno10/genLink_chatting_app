/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,jsx}",
  ],
  theme: {
    extend: {
      colors: {
        Chatwindow: '#1F1D1D',
        Sidebar: '#000000',
      },
    },
  },
  plugins: [],
}

