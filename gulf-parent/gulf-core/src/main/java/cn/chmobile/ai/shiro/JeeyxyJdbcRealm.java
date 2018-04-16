package cn.chmobile.ai.shiro;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.util.JdbcUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Jdbc 操作 Realm 
 * 能自动将登录认证时数据库列的数据填充到 UsernamePasswordToken 的相关列属性中
 * 
 * EasyUsernamePasswordEndcodeToken 子类自行实现加密处理 ，无需再 Realm 配置salt
 * 
 */
public class JeeyxyJdbcRealm extends AuthorizingRealm implements Serializable {
	
	private static final long serialVersionUID = 6521032955326295945L;

	/*--------------------------------------------
	|             C O N S T A N T S             |
	============================================*/
	/**
	 * The default query used to retrieve account data for the user.
	 */
	protected static final String DEFAULT_AUTHENTICATION_QUERY = "select * from users where username = ?";

	/**
	 * The default query used to retrieve the roles that apply to a user.
	 */
	protected static final String DEFAULT_USER_ROLES_QUERY = "select role_name from user_roles where username = ?";

	/**
	 * The default query used to retrieve permissions that apply to a particular
	 * role.
	 */
	protected static final String DEFAULT_PERMISSIONS_QUERY = "select permission from user_roles_permissions where username = ?";
	protected static final String DEFAULT_PASSWORD_COLUMN = "password";

	private static final Logger log = LoggerFactory.getLogger(JdbcRealm.class);

	/**
	 * Password hash salt configuration.
	 * <ul>
	 * <li>NO_SALT - password hashes are not salted.</li>
	 * <li>CRYPT - password hashes are stored in unix crypt format.</li>
	 * <li>COLUMN - salt is in a separate column in the database.</li>
	 * <li>EXTERNAL - salt is not stored in the database.
	 * {@link #getSaltForUser(String)} will be called to get the salt</li>
	 * </ul>
	 */

	/*--------------------------------------------
	|    I N S T A N C E   V A R I A B L E S    |
	============================================*/
	protected DataSource dataSource;

	protected String authenticationQuery = DEFAULT_AUTHENTICATION_QUERY;

	// 密码列名
	protected String passwordColumn = DEFAULT_PASSWORD_COLUMN;

	protected String userRolesQuery = DEFAULT_USER_ROLES_QUERY;
	protected String permissionsQuery = DEFAULT_PERMISSIONS_QUERY;
	// 是否查询授权权限信息
	protected boolean permissionsLookupEnabled = true;

	/*--------------------------------------------
	|         C O N S T R U C T O R S           |
	============================================*/

	/*--------------------------------------------
	|  A C C E S S O R S / M O D I F I E R S    |
	============================================*/

	/**
	 * Sets the datasource that should be used to retrieve connections used by
	 * this realm.
	 *
	 * @param dataSource
	 *            the SQL data source.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Overrides the default query used to retrieve a user's password during
	 * authentication. When using the default implementation, this query must
	 * take the user's username as a single parameter and return a single result
	 * with the user's password as the first column. If you require a solution
	 * that does not match this query structure, you can override
	 * {@link #doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)}
	 * or just {@link #getPasswordForUser(java.sql.Connection,String)}
	 *
	 * @param authenticationQuery
	 *            the query to use for authentication.
	 * @see #DEFAULT_AUTHENTICATION_QUERY
	 */
	public void setAuthenticationQuery(String authenticationQuery) {
		this.authenticationQuery = authenticationQuery;
	}

	/**
	 * Overrides the default query used to retrieve a user's roles during
	 * authorization. When using the default implementation, this query must
	 * take the user's username as a single parameter and return a row per role
	 * with a single column containing the role name. If you require a solution
	 * that does not match this query structure, you can override
	 * {@link #doGetAuthorizationInfo(PrincipalCollection)} or just
	 * {@link #getRoleNamesForUser(java.sql.Connection,String)}
	 *
	 * @param userRolesQuery
	 *            the query to use for retrieving a user's roles.
	 * @see #DEFAULT_USER_ROLES_QUERY
	 */
	public void setUserRolesQuery(String userRolesQuery) {
		this.userRolesQuery = userRolesQuery;
	}

	/**
	 * Overrides the default query used to retrieve a user's permissions during
	 * authorization. When using the default implementation, this query must
	 * take a role name as the single parameter and return a row per permission
	 * with three columns containing the fully qualified name of the permission
	 * class, the permission name, and the permission actions (in that order).
	 * If you require a solution that does not match this query structure, you
	 * can override
	 * {@link #doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)}
	 * or just
	 * {@link #getPermissions(java.sql.Connection,String,java.util.Collection)}
	 * </p>
	 * <p/>
	 * <b>Permissions are only retrieved if you set
	 * {@link #permissionsLookupEnabled} to true. Otherwise, this query is
	 * ignored.</b>
	 *
	 * @param permissionsQuery
	 *            the query to use for retrieving permissions for a role.
	 * @see #DEFAULT_PERMISSIONS_QUERY
	 * @see #setPermissionsLookupEnabled(boolean)
	 */
	public void setPermissionsQuery(String permissionsQuery) {
		this.permissionsQuery = permissionsQuery;
	}

	/**
	 * Enables lookup of permissions during authorization. The default is
	 * "false" - meaning that only roles are associated with a user. Set this to
	 * true in order to lookup roles <b>and</b> permissions.
	 *
	 * @param permissionsLookupEnabled
	 *            true if permissions should be looked up during authorization,
	 *            or false if only roles should be looked up.
	 */
	public void setPermissionsLookupEnabled(boolean permissionsLookupEnabled) {
		this.permissionsLookupEnabled = permissionsLookupEnabled;
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return super.supports(token);
	}

	/*--------------------------------------------
	|               M E T H O D S               |
	============================================*/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();

		// Null username is invalid
		if (username == null) {
			throw new AccountException("Null usernames are not allowed by this realm.");
		}

		Connection conn = null;
		SimpleAuthenticationInfo info = null;
		try {
			conn = dataSource.getConnection();

			String password = null;
			password = getUser(conn, username, upToken);
			if (password == null) {
				throw new UnknownAccountException("No account found for user [" + username + "]");
			}
			
			
			info = new SimpleAuthenticationInfo(username, password, getName());
		} catch (SQLException e) {
			final String message = "There was a SQL error while authenticating user [" + username + "]";
			if (log.isErrorEnabled()) {
				log.error(message, e);
			}
			
			// Rethrow any SQL errors as an authentication exception
			throw new AuthenticationException(message, e);
		} finally {
			JdbcUtils.closeConnection(conn);
		}
//		// 自动登录无需加密密码
//		if(SecurityUtils.getSubject().getSession().getAttribute("EasyShiro_AutoLogin")==null){
//			// 是否存在密码加密
//			try {
//				Method method=upToken.getClass().getMethod("encodePassword");
//				// 存在加密
//				if(method!=null){
//					String encodePwd=method.invoke(upToken).toString();
//					upToken.setPassword(encodePwd.toCharArray());
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				if (log.isInfoEnabled()) {
//					log.info("EasyJdbcRealm error!", e);
//				}
//			}
//		}
		
		//interceptor.afterDoGetAuthenticationInfo(info);
		return info;
	}
	
	private String getUser(Connection conn, String username, AuthenticationToken token) throws SQLException {
		String password = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(authenticationQuery);
			ps.setString(1, username);

			// Execute query
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();

			// Loop over results - although we are only expecting one result,
			// since usernames should be unique
			boolean foundResult = false;
			while (rs.next()) {

				// Check to ensure only one row is processed
				if (foundResult) {
					throw new AuthenticationException(
							"More than one user row found for user [" + username + "]. Usernames must be unique.");
				}

				for (int i = 1; i <= count; i++) {
					// 列名
					String columnLabel = rsmd.getColumnLabel(i);
					// // 列数据
					// Object value=rs.getObject(i);

					// 获取登录的数据库密码
					if (columnLabel.equalsIgnoreCase(passwordColumn)) {
						password = rs.getString(i);
					}
				
					
//					
//					for (int j = 0; j < tokenFields.length; j++) {
//						// 列名和EasyUsernamePasswordToken属性名相同，自动赋值
//						if (columnLabel.equalsIgnoreCase(tokenFields[j].getName())) {
//							tokenFields[j].setAccessible(true);
//							try {
//								if (tokenFields[j].getType().isAssignableFrom(String.class)) {
//									// tokenFields[j].set(token,
//									// Integer.valueOf(value.toString()));
//									tokenFields[j].set(token, rs.getString(i));
//								}
//								if (tokenFields[j].getType().isAssignableFrom(int.class)
//										|| tokenFields[j].getType().isAssignableFrom(Integer.class)) {
//									// tokenFields[j].set(token,
//									// Integer.valueOf(value.toString()));
//									tokenFields[j].set(token, rs.getInt(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(short.class)
//										|| tokenFields[j].getType().isAssignableFrom(Short.class)) {
//									// tokenFields[j].set(token,
//									// Short.valueOf(value.toString()));
//									tokenFields[j].set(token, rs.getShort(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(long.class)
//										|| tokenFields[j].getType().isAssignableFrom(Long.class)) {
//									// tokenFields[j].set(token,
//									// Long.valueOf(value.toString()));
//									tokenFields[j].set(token, rs.getLong(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(float.class)
//										|| tokenFields[j].getType().isAssignableFrom(Float.class)) {
//									// tokenFields[j].set(token,
//									// Float.valueOf(value.toString()));
//									tokenFields[j].set(token, rs.getInt(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(double.class)
//										|| tokenFields[j].getType().isAssignableFrom(Double.class)) {
//									// tokenFields[j].set(token,
//									// Double.valueOf(value.toString()));
//									tokenFields[j].set(token, rs.getDouble(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(boolean.class)
//										|| tokenFields[j].getType().isAssignableFrom(Boolean.class)) {
//									// tokenFields[j].set(token,
//									// Boolean.valueOf(value.toString()));
//									tokenFields[j].set(token, rs.getBoolean(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(char.class)
//										|| tokenFields[j].getType().isAssignableFrom(Character.class)) {
//									// tokenFields[j].set(token,
//									// value.toString().charAt(0));
//									tokenFields[j].set(token, rs.getString(i).charAt(0));
//								} else if (tokenFields[j].getType().isAssignableFrom(byte.class)
//										|| tokenFields[j].getType().isAssignableFrom(Byte.class)) {
//									// tokenFields[j].set(token,
//									// value.toString().charAt(0));
//									tokenFields[j].set(token, rs.getByte(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(java.util.Date.class)
//										|| tokenFields[j].getType().isAssignableFrom(java.sql.Date.class)) {
//									tokenFields[j].set(token, rs.getDate(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(java.sql.Time.class)) {
//									tokenFields[j].set(token, rs.getTime(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(String.class)) {
//									tokenFields[j].set(token, rs.getString(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(BigDecimal.class)) {
//									tokenFields[j].set(token, rs.getBigDecimal(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(java.sql.Blob.class)) {
//									tokenFields[j].set(token, rs.getBlob(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(java.sql.Clob.class)) {
//									tokenFields[j].set(token, rs.getClob(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(java.sql.NClob.class)) {
//									tokenFields[j].set(token, rs.getNClob(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(java.sql.RowId.class)) {
//									tokenFields[j].set(token, rs.getRowId(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(java.net.URL.class)) {
//									tokenFields[j].set(token, rs.getURL(i));
//								} else if (tokenFields[j].getType().isAssignableFrom(java.sql.Timestamp.class)) {
//									tokenFields[j].set(token, rs.getTimestamp(i));
//								} else {
//									try {
//										tokenFields[j].set(token, rs.getObject(i));
//									} catch (Exception e) {
//										if (log.isInfoEnabled()) {
//											log.info("EasyJdbcRealm error!", e);
//										}
//										e.printStackTrace();
//									}
//								}
//								
//
//							} catch (IllegalArgumentException e) {
//								if (log.isInfoEnabled()) {
//									log.info("EasyJdbcRealm error!", e);
//								}
//								e.printStackTrace();
//							} catch (IllegalAccessException e) {
//								if (log.isInfoEnabled()) {
//									log.info("EasyJdbcRealm error!", e);
//								}
//								e.printStackTrace();
//							}
//						}
//					}
//
				}

				foundResult = true;
			}
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(ps);
		}
		return password;
	}

//	@Override
//	protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
//		System.out.println("This is getAuthorizationInfo....");
//		String username = (String) getAvailablePrincipal(principals);
//		if (username != null) {
//			// 根据系统规则，获取用户的获取授权信息（将权限和角色）
//
//			// 我们可以通过用户名从数据库获取权限/角色信息
//			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//			// 将权限资源添加到用户信息中
//			Set<String> s = new HashSet<String>();
//			s.add("DoDept");
////			s.add("DoEmp");
//			info.setStringPermissions(s);
//			// 将角色资源添加到用户信息中
//			Set<String> r = new HashSet<String>();
//			r.add("role1");
//			info.setRoles(r);
//			
//			return info;
//		}
////		PermissionsAuthorizationFilter
//		return null;
//	}
	

	/**
	 * 初始化授权相关的角色列表和权限列表
	 *
	 * @see #getAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		
//		// null usernames are invalid
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}

		
		
		String username = (String) getAvailablePrincipal(principals);
		
		Connection conn = null;
		Set<String> roleNames = null;
		Set<String> permissions = null;
		try {
			conn = dataSource.getConnection();

			// Retrieve roles and permissions from database
			roleNames = getRoleNamesForUser(conn, username);
			if (permissionsLookupEnabled) {
				permissions = getPermissions(conn, username, roleNames);
			}

		} catch (SQLException e) {
			final String message = "There was a SQL error while authorizing user [" + username + "]";
			if (log.isErrorEnabled()) {
				log.error(message, e);
			}

			// Rethrow any SQL errors as an authorization exception
			throw new AuthorizationException(message, e);
		} finally {
			JdbcUtils.closeConnection(conn);
		}

	    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);
        //interceptor.afterDoGetAuthorizationInfo(info);
		return info;
		
//		return null;

	}

	protected Set<String> getRoleNamesForUser(Connection conn, String username) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<String> roleNames = new LinkedHashSet<String>();
		try {
			ps = conn.prepareStatement(userRolesQuery);
		/*
		 * # Oracle Driver ParameterMetaData bug
		 * The versions of the Oracle JDBC driver that seem to be affected are 12.1.0.1 and 12.1.0.2.
		 * The versions of the Oracle JDBC driver that seem to be unaffected are 11.2.0.4 and earlier.
		 * 
		 */
//			int paramCount=ps.getParameterMetaData().getParameterCount();
			int paramCount=userRolesQuery.length()-userRolesQuery.replace("?", "").length();
			for (int i = 1; i <= paramCount; i++) {
				ps.setString(i, username);
			}

			// Execute query
			rs = ps.executeQuery();
			// Loop over results and add each returned role to a set
			while (rs.next()) {

				String roleName = rs.getString(1);
				// Add the role to the list of names if it isn't null
				if (roleName != null) {
					roleNames.add(roleName);
				} else {
					if (log.isWarnEnabled()) {
						log.warn("Null role name found while retrieving role names for user [" + username + "]");
					}
				}
			}
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(ps);
		}
		return roleNames;
	}

	protected Set<String> getPermissions(Connection conn, String username, Collection<String> roleNames)
			throws SQLException {
		PreparedStatement ps = null;
		Set<String> permissions = new LinkedHashSet<String>();
		try {
			ps = conn.prepareStatement(permissionsQuery);
			/*
			 * # Oracle Driver ParameterMetaData bug
			 * The versions of the Oracle JDBC driver that seem to be affected are 12.1.0.1 and 12.1.0.2.
			 * The versions of the Oracle JDBC driver that seem to be unaffected are 11.2.0.4 and earlier.
			 * 
			 */
//			int paramCount=ps.getParameterMetaData().getParameterCount();
			
			int paramCount=permissionsQuery.length()-permissionsQuery.replace("?", "").length();

			
			for (int i = 1; i <= paramCount; i++) {
				ps.setString(i, username);
			}
//			for (String roleName : roleNames) {
//				ps.setString(1, roleName);
				ResultSet rs = null;

				try {
					// Execute query
					rs = ps.executeQuery();
					// Loop over results and add each returned role to a set
					while (rs.next()) {
						String permissionString = rs.getString(1);

						// Add the permission to the set of permissions
						if(permissionString!=null&&(!permissionString.trim().equals(""))){
							permissions.add(permissionString);
						}
					}
				} finally {
					JdbcUtils.closeResultSet(rs);
				}

//			}
		} finally {
			JdbcUtils.closeStatement(ps);
		}
		return permissions;
	}

	public void setPasswordColumn(String passwordColumn) {
		this.passwordColumn = passwordColumn;
	}

	public JeeyxyJdbcRealm() {
		super();
	}

	/**
	 * 刷新权限
	 */
	public static void reloadPermissions(){
		DefaultSecurityManager dwm=(DefaultSecurityManager) SecurityUtils.getSecurityManager();
		Collection<Realm> realms=dwm.getRealms();
		for (Realm realm : realms) {
			if(realm instanceof JeeyxyJdbcRealm){
				((JeeyxyJdbcRealm)realm).clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
			}
		}
	} 
	
	
}
