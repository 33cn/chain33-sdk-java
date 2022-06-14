
abstract contract TestA {
    function setValue(uint256  value)  external  virtual;
    function getValue()  external  virtual  view returns (uint256);
}

contract TestB {

    event SetAddress(address indexed from,address indexed testA);
    event SetValue(address indexed from,uint256 indexed value1,uint256 value2);

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
}