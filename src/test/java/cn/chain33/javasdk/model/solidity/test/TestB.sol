pragma solidity >=0.8;

abstract contract TestA {
    function setValue(uint256  value)  external  virtual;
    function getValue()  external  virtual  view returns (uint256);
}

contract TestB {

    event SetAddress(address indexed from,address indexed testA);
    event SetValue(address indexed from,uint256 indexed value1,uint256 value2);

    // 接收event
    event Received(address from,address to,uint256 amount);
    //拒收,回退event
    event Fallback(address,uint);

    TestA public _A;

    function setValue(uint256  _value)  external {
        _A.setValue(_value);
        emit SetValue(msg.sender,_value,_value);
    }

    function getValue() external view returns (uint256){
        uint256 value = _A.getValue();
        return value;
    }

    function setTestA(address a) external{
        _A = TestA(a);
        emit SetAddress(msg.sender,a);
    }

    function getTestA() external  view returns (address){
        return address(_A);
    }

    //测试向合约转账(存钱）
    //"send" and "transfer" are only available for objects of type "address payable", not "address".
    function deposit() payable public {
//        payable(address(this)).transfer(msg.value);
    }
    //测试从合约中提款  transfer1
    function withdraw1(address to,uint256 amount) payable public {
        if (!payable(to).send(amount)){
            emit  Fallback(address(this),amount);
        }else{
            emit Received(address(this),to,amount);
        }
    }
    //withdraw2
    function withdraw2(address to,uint256 amount) payable public {
        payable(to).transfer(amount);
    }


    // 获取合约账户余额
    function getBalanceOfContract() public view returns (uint256) {
        return address(this).balance;
    }

    //查看外部账户余额
    function getBalance(address addr) public view returns(uint256){
        return addr.balance;
    }

    //对函数方法的调用,触发receive回退函数
    function CallTransTest()payable public{
        (bool success,) = address(this).call(abi.encodeWithSignature("transferToContract()"));
    }

    fallback() external payable {
        emit Fallback(msg.sender,msg.value);
    }
    //通过transfer or send 方法对合约转账的化会触发 receive函数
    receive() external payable {
        emit Received(msg.sender,address(this),msg.value);
    }
}