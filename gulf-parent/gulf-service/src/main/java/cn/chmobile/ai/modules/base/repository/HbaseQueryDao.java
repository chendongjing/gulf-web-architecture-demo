package cn.chmobile.ai.modules.base.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Repository;

import cn.chmobile.ai.util.PageData;

@Repository
public class HbaseQueryDao {

	Logger logger = Logger.getLogger(HbaseQueryDao.class); 
	
	private String columnFamily = "columns";

	public String getColumnFamily() {
		return columnFamily;
	}

	public void setColumnFamily(String columnFamily) {
		this.columnFamily = columnFamily;
	}

	@Autowired
	private HbaseTemplate hbaseTemplate;

	public <T> T queryForBeanByRowKey(String tableName, String rowKey, final Class<T> beanType) {
		
		logger.info("-----------------------HbaseQueryDao.queryForBeanByRowKey-----------------------------------");
		logger.info("tableName:" + tableName + ",rowKey:" + rowKey);
		logger.info("-----------------------HbaseQueryDao.queryForBeanByRowKey-----------------------------------");
        return hbaseTemplate.get(tableName, rowKey, new RowMapper<T>() {  
            public T mapRow(Result result, int rowNum) throws Exception {  

            	Map<byte[], byte[]> map = result.getFamilyMap(Bytes.toBytes(columnFamily));
            	T t = beanType.newInstance();
            	BeanWrapper beanWrapper = new BeanWrapperImpl(t);
                for(Map.Entry<byte[], byte[]> entry : map.entrySet()){
                	beanWrapper.setPropertyValue(Bytes.toString(entry.getKey()),Bytes.toString(entry.getValue()));
                }
                return t;  
            }  
        });  
	}
	
	/**
	 * 根据精确的rowKey查询
	 * @param tableName
	 * @param rowKey
	 * @return
	 */
	public Map<String, String> queryForMapByRowKey(String tableName, String rowKey) {
		
		logger.info("-----------------------HbaseQueryDao.queryForMapByRowKey-----------------------------------");
		logger.info("tableName:" + tableName + ",rowKey:" + rowKey);
		logger.info("-----------------------HbaseQueryDao.queryForMapByRowKey-----------------------------------");
        return hbaseTemplate.get(tableName, rowKey, new RowMapper<Map<String, String>>() {  
            public Map<String, String> mapRow(Result result, int rowNum) throws Exception {  

            	Map<byte[], byte[]> mapColumn = result.getFamilyMap(Bytes.toBytes(columnFamily));
            	Map<String, String> map = new HashMap<String, String>();
                for(Map.Entry<byte[], byte[]> entry : mapColumn.entrySet()){
                	map.put(Bytes.toString(entry.getKey()),Bytes.toString(entry.getValue()));
                }

                return map;  
            }  
        });  
	}
	
   /**
    * 根据startRow列表
    * @param tableName
    * @param startRow
    * @param stopRow
    * @return
    */
	public List<PageData> queryForListByScanRange(String tableName, String startRow, String stopRow) {
		
		logger.info("-----------------------HbaseQueryDao.queryForListByScanRange-----------------------------------");
		logger.info("tableName:" + tableName + ",startRow:" + startRow+ ",stopRow:" + stopRow);
		logger.info("-----------------------HbaseQueryDao.queryForListByScanRange-----------------------------------");
		
		Scan scan = new Scan();  
		if (startRow != null) {
			scan.setStartRow(Bytes.toBytes(startRow));
        } else {
        	scan.setStartRow(Bytes.toBytes(""));
        }
        if (stopRow != null) {
        	scan.setStopRow(Bytes.toBytes(stopRow));
        } else {
        	scan.setStopRow(Bytes.toBytes(""));
        }
        
        Filter pf = new PrefixFilter(Bytes.toBytes(startRow));
        scan.setFilter(pf);

        return hbaseTemplate.find(tableName, scan, new RowMapper<PageData>() {
			
            public PageData mapRow(Result result, int rowNum) throws Exception {  

            	Map<byte[], byte[]> mapColumn = result.getFamilyMap(Bytes.toBytes(columnFamily));
            	PageData map = new PageData();
                for(Map.Entry<byte[], byte[]> entry : mapColumn.entrySet()){
                	map.put(Bytes.toString(entry.getKey()), Bytes.toString(entry.getValue()));
                }
                return map;  
            }  
        });  
	}

	public void save(String tableName, String rowKey, Map<String,String> rowMap) {

		for (Entry<String, String> en : rowMap.entrySet()) {
			hbaseTemplate.put(tableName, rowKey, columnFamily, en.getKey(), Bytes.toBytes(en.getValue()));
		}
	}
	
	public void delete(String tableName, final String rowKey) {
		
		  hbaseTemplate.execute(tableName, new TableCallback<Boolean>() {  
		        public Boolean doInTable(HTableInterface table) throws Throwable {  
		            boolean flag = false;  
		            try{  
		                List<Delete> list = new ArrayList<Delete>();  
		                Delete d1 = new Delete(rowKey.getBytes());  
		                list.add(d1);  
		            	table.delete(list); 
		             flag = true;  
		            }catch(Exception e){  
		                e.printStackTrace();  
		            }  
		            return flag;  
		        }  
		    });  
	}
	
}
