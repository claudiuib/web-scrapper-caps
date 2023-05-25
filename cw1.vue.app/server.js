const  express = require('express')
const url = require("url");

const app = express();

//Status codes defined in external file
require('./http_status.js');

const db = require('./database.js');


//Serve up everything in public
app.use(express.static('public'));
// //Get the request for all products
app.get('/caps', async (request, response) => {
  let  caps = await db.getCaps();
  //send back to the client
  response.json(caps);
});





//GET request for product by ID
//If the last part of the path is a valid product id, return data about that product


app.get('/caps/*', (request, response) =>{
  //Get the last part of the path
  const pathEnd = getPathEnd(request.url);

  //Check that it is a valid ID
  let regEx = new RegExp('^[0-9]+$');//RegEx returns true if string is all digits.
  if(regEx.test(pathEnd)){
      //Run search and then send back results
      db.getCap(pathEnd).then( cap => {
          response.json(cap)
      }).catch(error => console.log(error.message));;
  }
  else{
      response.status(HTTP_STATUS.NOT_FOUND);
      response.send("{error: 'Invalid cap ID', url: " + request.url + "}");
  }
});

// get compare


app.get('/compare/*', (request, response) =>{
  //Get the last part of the path
  const pathEnd = getPathEnd(request.url);
  //Check that it is a valid ID
  let regEx = new RegExp('^[0-9]+$');//RegEx returns true if string is all digits.
  if(regEx.test(pathEnd)){
      //Run search and then send back results
      db.getComparison(pathEnd).then( compare => {
          response.json(compare)
      }).catch(error => console.log(error.message));;
  }
  else{
      response.status(HTTP_STATUS.NOT_FOUND);
      response.send("{error: 'Invalid cap ID', url: " + request.url + "}");
  }
});


 //get search
app.get('/search/:name',(request,response)=>{
  const nameQ=request.params.name;
  db.getSearch(nameQ).then(data=>{
      response.json(data);
  })
});

//Returns the last part of the path
function getPathEnd(urlStr){
  //Parse the URL
  let urlObj = url.parse(urlStr, true);

  //Split the path of the request into its components
  let pathArray = urlObj.pathname.split("/");

  //Return the last part of the path
  return pathArray[pathArray.length - 1];
}


  


//Start the app listening on port 8080
app.listen(3030,()=>{
    console.log('server is running');
})

//Export server for testing
module.exports = app;