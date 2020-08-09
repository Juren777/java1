package ru.progwards.java1.lessons.io2;

public class PhoneNumber {

    public static String format(String phone){
        StringBuilder sb = new StringBuilder();
        for (char c: phone.toCharArray()){
            if (Character.isDigit(c)){
                sb.append(c);
            }
        }
        int len = sb.length();
        if (len == 10){
            sb.insert(0,'+');
            sb.insert(1,'7');
            sb.insert(2,'(');
            sb.insert(6,')');
            sb.insert(10,'-');
        } else if (len == 11){
            sb.deleteCharAt(0);
            sb.insert(0,'+');
            sb.insert(1,'7');
            sb.insert(2,'(');
            sb.insert(6,')');
            sb.insert(10,'-');
        } else {
            throw new RuntimeException("Wrong format!");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(format("8(999)111-22-33"));
    }
}
