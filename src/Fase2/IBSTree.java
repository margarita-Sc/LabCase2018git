package Fase2;

public interface IBSTree {
		public void insertUser(Usuario elem);
		public void removeUser(String username);
		public String findUser(String fullName);
		public void show();
		public void showLevel();
		public void complaint(String username);
		public String extremeUsers();
		

}
