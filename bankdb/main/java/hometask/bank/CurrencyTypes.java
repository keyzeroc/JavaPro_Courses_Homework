package hometask.bank;

public enum CurrencyTypes {
    UAH,
    USD,
    EUR;

    public static boolean isType(String str){
        try {
            valueOf(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
