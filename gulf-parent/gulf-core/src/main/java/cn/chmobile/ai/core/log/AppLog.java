package cn.chmobile.ai.core.log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class AppLog {
	
	private static final Logger DEFAULT_LOGGER = LoggerFactory.getLogger(AppLog.class);
	private static final Map<Class<?>, Logger> TAGGED_LOGGERS = new HashMap<Class<?>, Logger>();

	private static HashMap<Integer, String> optionalInfoBuff = new HashMap<Integer, String>();

	private enum Operation{
		RESET,
		ADD,
		REPLACE,
		OVERRIDE,
		REMOVE
	}

	private AppLog() {}

	public static void d(String message){
		d(DEFAULT_LOGGER, message);
	}

	public static void d(String message,Object...objects){
		d(DEFAULT_LOGGER, message, objects);
	}

	public static void d(String message,Throwable e){
		d(DEFAULT_LOGGER, message, e);
	}

	public static void d(Class<?> tag,String message){
		d(getLogger(tag), message);
	}

	public static void d(Class<?> tag,String message,Object...objects){
		d(getLogger(tag), message,objects);
	}

	public static void d(Class<?> tag,String message,Throwable e){
		d(getLogger(tag), message,e);
	}
	private static void d(Logger logger,String message){
		if(logger.isDebugEnabled()){
			logger.debug(message);
		}
	}
	private static void d(Logger logger,String message,Object...objects){
		if(logger.isDebugEnabled()){
			logger.debug(message,objects);
		}
	}
	private static void d(Logger logger,String message,Throwable e){
		if(logger.isDebugEnabled()){
			logger.debug(message, e);
		}
	}

	public static void i(String message){
		i(DEFAULT_LOGGER, message);
	}

	public static void i(String message,Object...objects){
		i(DEFAULT_LOGGER, message, objects);
	}

	public static void i(String message,Throwable e){
		i(DEFAULT_LOGGER, message, e);
	}

	public static void i(Class<?> tag,String message){
		i(getLogger(tag), message);
	}

	public static void i(Class<?> tag,String message,Object...objects){
		i(getLogger(tag), message,objects);
	}

	public static void i(Class<?> tag,String message,Throwable e){
		i(getLogger(tag), message,e);
	}
	private static void i(Logger logger,String message){
		if(logger.isInfoEnabled()){
			logger.info(message);
		}
	}
	private static void i(Logger logger,String message,Object...objects){
		if(logger.isInfoEnabled()){
			logger.info(message,objects);
		}
	}
	private static void i(Logger logger,String message,Throwable e){
		if(logger.isInfoEnabled()){
			logger.info(message, e);
		}
	}

	public static void t(String message){
		t(DEFAULT_LOGGER, message);
	}

	public static void t(String message,Object...objects){
		t(DEFAULT_LOGGER, message, objects);
	}

	public static void t(String message,Throwable e){
		t(DEFAULT_LOGGER, message, e);
	}

	public static void t(Class<?> tag,String message){
		t(getLogger(tag), message);
	}

	public static void t(Class<?> tag,String message,Object...objects){
		t(getLogger(tag), message,objects);
	}

	public static void t(Class<?> tag,String message,Throwable e){
		t(getLogger(tag), message,e);
	}
	private static void t(Logger logger,String message){
		if(logger.isTraceEnabled()){
			logger.trace(message);
		}
	}
	private static void t(Logger logger,String message,Object...objects){
		if(logger.isTraceEnabled()){
			logger.trace(message,objects);
		}
	}
	private static void t(Logger logger,String message,Throwable e){
		if(logger.isTraceEnabled()){
			logger.trace(message, e);
		}
	}

	public static void w(String message){
		w(DEFAULT_LOGGER, message);
	}

	public static void w(String message,Object...objects){
		w(DEFAULT_LOGGER, message, objects);
	}

	public static void w(String message,Throwable e){
		w(DEFAULT_LOGGER, message, e);
	}

	public static void w(Class<?> tag,String message){
		w(getLogger(tag), message);
	}

	public static void w(Class<?> tag,String message,Object...objects){
		w(getLogger(tag), message,objects);
	}

	public static void w(Class<?> tag,String message,Throwable e){
		w(getLogger(tag), message,e);
	}
	private static void w(Logger logger,String message){
		if(logger.isWarnEnabled()){
			logger.warn(message);
		}
	}
	private static void w(Logger logger,String message,Object...objects){
		if(logger.isWarnEnabled()){
			logger.warn(message,objects);
		}
	}
	private static void w(Logger logger,String message,Throwable e){
		if (logger.isTraceEnabled()
				|| logger.isDebugEnabled()) {
			logger.warn(message, e);
		} else if (logger.isWarnEnabled()) {
			logger.warn(message , e);
		}
	}

	public static void e(String message){
		e(DEFAULT_LOGGER, message);
	}

	public static void e(String message,Object...objects){
		e(DEFAULT_LOGGER, message, objects);
	}

	public static void e(String message,Throwable e){
		e(DEFAULT_LOGGER, message, e);
	}

	public static void e(Class<?> tag,String message){
		e(getLogger(tag), message);
	}

	public static void e(Class<?> tag,String message,Object...objects){
		e(getLogger(tag), message,objects);
	}

	public static void e(Class<?> tag,String message,Throwable e){
		e(getLogger(tag), message,e);
	}
	private static void e(Logger logger,String message){
		if(logger.isErrorEnabled()){
			logger.error(message);
		}
	}
	private static void e(Logger logger,String message,Object...objects){
		if(logger.isErrorEnabled()){
			logger.error(message,objects);
		}
	}
	private static void e(Logger logger,String message,Throwable e){
		if(logger.isErrorEnabled()){
			logger.error(message, e);
		}
	}

	public static Logger getLogger(Class<?> tag){
		Logger logger = TAGGED_LOGGERS.get(tag);
		if(logger == null){
			logger = LoggerFactory.getLogger(tag);
			TAGGED_LOGGERS.put(tag, logger);
		}
		return logger;
	}

	public static String getOptionLogBuff(){

		String buff = "";
		Iterator<Entry<Integer, String>> it = optionalInfoBuff.entrySet().iterator() ;
		int num = 1;
		while(it.hasNext()){
			buff += "\t#" + num + "\t" + it.next() + "\n";
			num++;
		}
		return buff;
	}
	
	public static int addOptionaLogBuff(String info) {
		return operationOptionInfo(Operation.ADD, info);
	}

	public static void replaceOptionalLogBuff(int key, String info){
		operationOptionInfo(Operation.REPLACE, info, key);
	}


	public static int overrideOptionalLogBuff(String info){
		return operationOptionInfo(Operation.OVERRIDE, info);
	}

	public static void removeOptionalLogBuff(int key){
		operationOptionInfo(Operation.REMOVE, key);
	}

	public static void resetOptionalLogBuff(){
		operationOptionInfo(Operation.RESET);
	}

	private static int operationOptionInfo(Operation op){
		return operationOptionInfo(op, null);
	}

	private static int operationOptionInfo(Operation op, int key){
		return operationOptionInfo(op, null, key);
	}

	private static int operationOptionInfo(Operation op, String info){
		return operationOptionInfo(op, info, null);
	}

	private static int operationOptionInfo(Operation op, String info, Integer key){

		int result = -1;
		synchronized(optionalInfoBuff){
			switch(op){
				case RESET :
					optionalInfoBuff.clear();
					break;
				case ADD :
					key = optionalInfoBuff.size();
					optionalInfoBuff.put(key, info);
					result = key;
					break;
				case REPLACE :
					optionalInfoBuff.put(key, info);
					break;
				case OVERRIDE :
					resetOptionalLogBuff();
					key = optionalInfoBuff.size();
					optionalInfoBuff.put(key, info);
					result = key;
					break;
				case REMOVE :
					optionalInfoBuff.remove(key);
					break;
			}
		}
		return result;
	}
}