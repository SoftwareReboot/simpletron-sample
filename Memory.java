public class Memory {
   private static String[] addresses;
   private static String currAcc;
   private static int currCounter;
      
   public Memory(int size) {
      addresses = new String[size];
      currAcc = new String("+0000");
      currCounter = 0;   
      
      for (int i = 0; i < size; i++)
         addresses[i] = "0000";
   }
   
   public void addItem (int index, String value) {
      addresses[index] = value;
   }
   
   public static String getItem (int index) {
      return addresses[index];  
   }

   public static String[] getAdds() {
      return addresses;
   }
   
   public static String getCurrAcc() {
      return currAcc;
   }
   
   public static void setAcc(String value) {
      currAcc = value;
   }

   public static int getCurrCounter() {
      return currCounter;
   }
   
   public static int getMemSize() {
      return addresses.size;
   }
}
