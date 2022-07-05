// SPDX-License-Identifier: SimPL-2.0
pragma solidity ^0.8.1;
import "https://github.com/OpenZeppelin/openzeppelin-contracts/blob/master/contracts/token/ERC1155/ERC1155.sol";

contract newERC1155 is ERC1155 {

    address public _owner;
    mapping(uint256 => string) private _tokenURI;

    constructor() ERC1155("") {
        _owner = msg.sender;
    }

    /**
     * 初始化NFT资产
     * _to:NFT发行在哪个地址下
     * ids: NFT资产数组
     * amounts: NFT数额，和上面的ids长度要保持一致，并且一一对应
     * uris: NFT的URI信息，和上面的ids长度要保持一致，并且一一对应
     */
    function mint(address _to, uint256[] memory ids, uint256[] memory amounts, string[] memory uris) external {
        require(msg.sender == _owner, "only authorized owner can mint NFT.");
        require(ids.length == amounts.length, "The ids and amounts are not match");
        require(ids.length == uris.length, "The ids and uris are not match");
        _mintBatch(_to, ids, amounts, "");
        if (uris.length > 0) {
            for (uint256 i = 0; i < ids.length; i++) {
                _setURI(ids[i], uris[i]);
            }
        }
    }

    /**
     * 转让NFT
     * to: 转让的去向地址
     * id: NFT编号
     * amount: 转让数量
     */
    function transferArtNFT(address to, uint256 id, uint256 amount) external {
        // 转账
        safeTransferFrom(msg.sender, to, id, amount, "");
    }

    /**
     * 设置NFT URI信息
     * id: NFT编号
     * uri: URI信息
     */
    function _setURI(uint256 _id, string memory _uri) internal {
        _tokenURI[_id] = _uri;
    }

    function uri(uint256 _id) public view virtual override returns (string memory) {
        return _tokenURI[_id];
    }

}