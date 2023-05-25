
var APP_LOG_LIFECYCLE_EVENTS = true;
  var app = new Vue({

    el: '#app',
    data: {
      products: [],
      searchName: '',

      productDetails: [],
      compare: [],
      showProducts:true



    },

    created() {
      this.getProducts();

    },
    created() {
      this.getSearch();
    },

    created() {
      this.compareProduct();
    },
  

    
    methods: {
      // Get All Products
      async getProducts() {
        try {
          const response = await axios.get('http://localhost:3030/caps');
          this.products = response.data;
        } catch (err) {

        }
      },



      //search the products
      async getSearch() {
        try {
          const response = await axios.get(`http://localhost:3030/search/${this.searchName}`);
          this.products = response.data;
        } catch (err) {
          console.log(err);
        }
      },
      //product details
      async compareProduct(id) {
        try {
          const response = await axios.get("http://localhost:3030/caps/" + id);
          this.productDetails = response.data;
          const response2 = await axios.get("http://localhost:3030/compare/" + id);
          this.compare = response2.data;
          this.showProducts = this.showProducts ? false : true;
        } catch (err) {
          console.log(err);
        }

      },
      
     
     
    },

  })
