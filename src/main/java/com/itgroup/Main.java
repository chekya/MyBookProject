package com.itgroup;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DataManager manager = new DataManager();

        while (true) {
            System.out.println("메뉴 선택");
            System.out.println("0:종료, 1:전체조회, 2:등록, 3:수정, 4:삭제, 5:책수, 6:제목조회, 7:카테고리조회, 8:ebook조회, 9:ID조회, 10:제목으로 상세조회, 11:제목으로 ebook 제공 여부 조회");
            int menu = scan.nextInt(); scan.nextLine();

            switch (menu) {
                case 0:
                    System.out.println("프로그램 종료");
                    System.exit(0);
                case 1:
                    manager.selectAll();
                    break;
                case 2:
                    manager.insertData();
                    break;
                case 3:
                    manager.updateData();
                    break;
                case 4:
                    manager.deleteData();
                    break;

                case 5:
                    manager.getSize();
                    break;
                case 6:
                    manager.getOne();  //제목으로 조회
                    break;
                case 7:
                    manager.getBookByCategory();
                    break;
                case 8:
                    manager.checkBookExists();
                    break;
                case 9:
                    manager.getBookById();   //ID로 조회
                    break;
                case 10:
                    manager.showBookInfoByTitle();
                    break;
                case 11:
                    manager.checkEbookByTitle();
                    break;
                default:
                    System.out.println("잘못된 번호입니다. 다시 입력하여 주십시오");

            }
        }
    }
}
