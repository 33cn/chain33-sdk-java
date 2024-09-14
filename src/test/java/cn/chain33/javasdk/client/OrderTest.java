package cn.chain33.javasdk.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import cn.chain33.javasdk.model.rpcresult.AccountAccResult;
import cn.chain33.javasdk.model.rpcresult.AccountResult;
import cn.chain33.javasdk.model.rpcresult.BooleanResult;
import cn.chain33.javasdk.model.rpcresult.QueryTransactionResult;
import cn.chain33.javasdk.model.rpcresult.TokenBalanceResult;
import cn.chain33.javasdk.model.rpcresult.TxResult;
import cn.chain33.javasdk.model.rpcresult.WalletStatusResult;
import cn.chain33.javasdk.utils.HexUtil;
import cn.chain33.javasdk.utils.TransactionUtil;

public class RpcClientTest {

	// 区块链节点IP
	String ip = "121.52.224.92";
	// 平行链服务端口
	int port = 8801;
    RpcClient client = new RpcClient(ip, port);
	

    /**
     * 订单hash校验
     * 
     * @throws IOException
     */
    @Test
    public void checkHash() throws IOException {
    	String hash = "0x9895a7202c80d23d316ef9af68ef8191e9f4ef85598446cca73dfd59c07b9d03";
    	System.out.println(queryTransaction(hash));
    	
    	
//    	
//    	String excelFilePath = "C:\\Users\\andy\\Desktop\\orderRecords.xls"; // 替换为你的Excel文件路径
//    	List<List<String>> data = readExcelFile(excelFilePath);
//    	System.out.println(data);
    }
    
    private List<List<String>> readExcelFile(String filePath) {
    	List<List<String>> dataList = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // 读取第一个工作表
            int rowCount = sheet.getLastRowNum() + 1; // 获取行数
            int cellCount = 0;

            // 遍历每一行
            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                cellCount = row.getLastCellNum(); // 获取列数

                // 遍历每一个单元格
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        rowData.add(String.valueOf(cell.getStringCellValue()));
                    } else if (cell.getCellType() == CellType.STRING) {
                        rowData.add(Integer.parseInt(cell.getStringCellValue()));
                    }
                }
                dataList.add(rowData);
            }
    }
        return dataList;
}
