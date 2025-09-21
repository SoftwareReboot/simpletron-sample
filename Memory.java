public class Memory {
   private static String[] addresses;
      
   public Memory(int size) {
      addresses = new String[size];   
      
      for (int i = 0; i < size; i++)
         addresses[i] = "0000";
   }
   
   public void addItem (int index, String value) {
      addresses[index] = value;
   }
   
   public String getItem (int index) {
      return addresses[index];  
   }

   public String[] getAdds() {
      return addresses;
   }
   
   public int getMemSize() {
      return addresses.length;
   }
}
