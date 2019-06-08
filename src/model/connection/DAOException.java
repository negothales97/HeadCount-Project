package model.connection;

import java.sql.SQLException;

public class DAOException extends SQLException {

	public DAOException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DAOException(String arg0, String arg1, int arg2, Throwable arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String arg0, String arg1, int arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String arg0, String arg1, Throwable arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DAOException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
