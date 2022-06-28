pragma solidity >=0.8;

contract TestA {

    uint256 public _value;

    address public _B;

    constructor(address b) public {
        _B=b;
    }
   //调用鉴权
    modifier authorised() {
        require(msg.sender==_B,"You don't have access!");
        _;
    }
    function setValue(uint256  value)  external  authorised(){
       _value=value;
    }

    function getValue() external view returns (uint256){
        return _value;
    }
}