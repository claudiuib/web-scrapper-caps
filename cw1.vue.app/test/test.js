


//database

let db =require('../database');
//Built in Node.js assertions
const assert = require ("assert");

//Chai library for HTTP requests and more sophisticated assertions
let chai = require('chai');
let chaiHttp = require ('chai-http');
let should = chai.should();
let expect = chai.expect;
chai.use(chaiHttp);

//Import server
let server = require('../server');
describe('Server Tests ',function(){
//Mocha/chai test for the method Get/caps
 
describe("GET /caps", () => {
    it("responds with an array of caps", async () => {
      const response = await chai.request(server)
        .get("/caps");
  
      expect(response).to.have.status(200);
      expect(response.body).to.be.an("array");
    });
  });
  //Mocha test for the getCap method

  describe('Test for getCap',()=>{
    it('should return an object for the specific cap',(done)=>{
      chai.request(server)
        .get('/caps/1')
        .end((err,res)=>{
          res.should.have.status(200);
          res.should.should.be.a('object');
          
         

          done();
        });
    });
  });
});
