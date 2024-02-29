
function TreeNode(val) {
    this.val = val;
    this.left = this.right = null;
}

/**
 * Encodes a tree to a single string.
 *
 * @param {TreeNode} root
 * @return {string}
 */
var serialize = function(root) {
    let result = [];
    encode(root, result);

    let res = result.join(",");
    console.log(res);
    return res;
};

var encode = function(node, result) {
    if(!node) result.push("X");
    else {
        result.push(node.val.toString());
        encode(node.left, result);
        encode(node.right, result);
    }
}
/**
 * Decodes your encoded data to tree.
 *
 * @param {string} data
 * @return {TreeNode}
 */
var deserialize = function(data) {
    let data_arr = data.split(",");
    return decode(data_arr);
};

let i = 0;
var decode = function(data_arr) {
    if( i >= data_arr.length) return null;
    let curItem = data_arr[i++];
    if( curItem === "X") return null;

    let node = new TreeNode(Number(curItem));
    node.left = decode(data_arr);
    node.right = decode(data_arr);

    return node;
}

/**
 * Your functions will be called as such:
 * deserialize(serialize(root));
 */

serialize(deserialize("1,2,3,X,4,X,X,5,6,7,8,X,X,X,X"))