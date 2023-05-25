//Import the mysql module

const mysql = require('mysql');

//Create a connection object with the user details
const connectionPool = mysql.createPool({
    connectionLimit: 1,
    host: "localhost",
    user: "root",
    password: "",
    database: "caps",
    debug: false
});


//return all products - id ,names and images
module.exports.getCaps = async () => {
    let sql ="SELECT c.id , c.name, ci.image_url    FROM caps c INNER JOIN caps_instance ci on c.id = ci.caps_id";

  return executeQuery(sql);

};



/** Returns a promise to get details  about a single product from both tables caps and caps_instance  */
module.exports.getCap = (capId) => {
    const sql = "SELECT c.id ,c.style_key,  c.name,ci.colour, ci.image_url    FROM caps c INNER JOIN caps_instance ci on c.id = ci.caps_id WHERE c.id =" + capId;
    return executeQuery(sql);
}

//return a promise with common price and link by specifing id
module.exports.getComparison= (compareID)=>{
    const sql= "SELECT c.price, c.url FROM compare  c INNER JOIN caps_instance ci on c.instance_id = ci.id INNER JOIN caps cis on ci.caps_id = cis.id  WHERE cis.id ="+compareID;
    return executeQuery(sql)
}
//search for caps by name

module.exports.getSearch= async (searchTerm) => {
    const sql = "SELECT c.id, c.name,ci.image_url  FROM caps c INNER JOIN caps_instance ci on c.id =ci.caps_id WHERE c.name LIKE '%" + searchTerm + "%' ";
 
    return executeQuery(sql);
 }

/** Wraps connection pool query in a promise and returns the promise */
async function executeQuery(sql){
    //Wrap query around promise
    let queryPromise = new Promise( (resolve, reject)=> {
        //Execute the query
        connectionPool.query(sql, function (err, result) {
            //Check for errors
            if (err) {
                //Reject promise if there are errors
                reject(err);
            }

            //Resole promise with data from database.
            resolve(result);
        });
    });
       //Return promise
       return queryPromise;
    }

//check if database is connected
connectionPool.getConnection((err, connection) => {
    if(err) {
        console.error(`Error: ${err.message}`);
        return;
    }
    console.log("Connected successfully!");
    connection.release();
});





