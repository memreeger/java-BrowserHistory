package Homework;

import java.util.ArrayList;

public class browserHistory {
    public static pageHistory head;
    public static pageHistory current;
    static ArrayList<String> allVisited = new ArrayList<>();

    public static void main(String[] args) {

        visit("reddit1");
        visit("reddit2");
        visit("reddit3");
        visit("reddit4");
        visit("reddit5");
        visit("reddit6");
        visit("reddit7");
        visit("reddit8");
        visit("reddit9");
        visit("reddit10");

        System.out.print("list: ");
        printList(head); // github -> facebook -> google
        System.out.println("current: " + current.url); // github

        back();
        back();
        back();
        back();
        back();
        forward();

        System.out.println("visit: deneme");
        visit("deneme");
        System.out.print("list: ");
        printList(head); // reddit -> github -> facebook -> google
        System.out.println("current: " + current.url); // reddit

        System.out.println("All visited pages:");
        printAllHistory(allVisited);

        deleteBetween("reddit6","reddit6");
        printAllHistory(allVisited);
    }

    public static void deleteBetween(String startUrl, String endUrl) {

        int start = allVisited.indexOf(startUrl);
        int end = allVisited.lastIndexOf(endUrl);

        if (start == -1 || end == -1) {
            System.out.println("URL bulunamadı");
            return;
        }

        if (start == end) {
            System.out.println("Sadece 1 adet aynı url mevcut. Aralık yok.");
            return;
        }

        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        allVisited.subList(start + 1, end).clear();
    }

    public static void printAllHistory(ArrayList<String> history){
        for(int i = 0; i < history.size(); i++){
            if(i == (history.size() - 1)){
                System.out.print(history.get(i));
            }else{
                System.out.print(history.get(i) + " -> ");
            }
        }
        System.out.println();
    }


    public static void printList(pageHistory head) {
        pageHistory temp = head;
        while(temp != null) {
            System.out.print(temp.url);
            if(temp.next != null){
                System.out.print(" -> ");
            }else{
                System.out.println();
            }
            temp = temp.next;
        }
    }



    public static void visit(String url) {
        pageHistory newPage = new pageHistory(url);
        allVisited.add(0, url);

        if (head == null) {
            head = newPage;
            current = newPage;
            return;
        }

        //visit işlemi yaptığımız zaman back yapılıp yapılmadığını kontrol etmek için
        //yani current'in forward bağları varsa kopartmak için
        if (current.prev != null) {
            current.prev.next = null; // iki taraftan bağlantıyı koparmak için yaptık
            current.prev = null;
        }

        newPage.next = current;
        current.prev = newPage;

        head = newPage;
        current = newPage;
    }

    public static void back(){
        if(current.next != null){
            current = current.next;
            allVisited.add(0, current.url);
            System.out.println("(back) : " + current.url);
        }else{
            System.out.println("geri gidecek sayfa yok!");
        }
    }

    public static void forward(){
        if(current.prev != null){

            current = current.prev;
            allVisited.add(0, current.url);
            System.out.println("(forward) : " + current.url);
        }else{
            System.out.println("ileri gidecek sayfa yok !");
        }
    }

}


class pageHistory{
    String url;
    pageHistory next;
    pageHistory prev;

    public pageHistory(String url){
        this.url = url;
    }

}
