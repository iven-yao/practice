var async = require('async');

const waterfall = () => async.waterfall([
	function fun1(done) {
    console.log(done.toString().match(/function[^{]+\{([\s\S]*)\}$/)[1]);
  	done(null, 'value1');
  },
  function fun2(value1, done) {
  	console.log(value1);
    done(null, ['v','a','l','u','e','2']);
  },
  function fun3(value2, done) {
  	console.log(value2);
    done(null);
  }
], (err) => {
	if(err) console.log(err);
  else console.log("no error");
});

module.exports = {waterfall};