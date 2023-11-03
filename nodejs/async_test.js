var async = require('async');

const waterfall = () => async.waterfall([
  // first argument in callback function is error code, others are arguments for next function
  // the 'last' argument in each function should be callback function
  // the callback function doesn't have to be named exactly as callback, it could be anything, like done, like next etc.
	function fun1(callback) {
    console.log('inside of the callback function:')
    console.log(callback.toString());
  	callback(null, 'value1');
  },
  function fun2(value1, callback) {
  	console.log(value1);
    callback(null, ['v','a','l','u','e','2']);
  },
  function fun3(value2, callback) {
  	value2.forEach(e => console.log(e));
    callback(null, 'value3', 'another_value3');
  },
  function fun4(value3, another_value3, callback) {
    console.log(value3, another_value3);
  }
], (err) => {
	if(err) console.log(err);
  else console.log("no error");
});

module.exports = {waterfall};