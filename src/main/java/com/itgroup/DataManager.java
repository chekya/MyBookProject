package com.itgroup;

import com.itgroup.dao.BookDao;
import com.itgroup.bean.Book;
import java.util.List;
import java.util.Scanner;

public class DataManager {
    private BookDao bdao = null;
    private Scanner scan = null;

    public DataManager() {
        this.bdao = new BookDao();
        this.scan = new Scanner(System.in);
    }

    // 1. 전체 책 조회
    public void selectAll() {
        List<Book> list = bdao.selectAll();
        if (list.isEmpty()){
            System.out.println("등록된 책이 없습니다.");
            return;
        }
        System.out.println("전체 책 목록 (" + list.size() + "권)");
        System.out.println("ID\t제목\t저자\t출판사\t출판일\t페이지\t카테고리\t가격\t전자책");
        System.out.println("=".repeat(120));
        for (Book book : list) {
            System.out.println(book.showBookInfo());
        }
    }

    // 2. 책 등록
    public void insertData() {
        Book book = new Book();

        System.out.println("제목 입력: ");
        book.setTitle(scan.nextLine());
        System.out.println("저자 입력: ");
        book.setAuthor(scan.nextLine());
        System.out.println("출판사 입력: ");
        book.setPublisher(scan.nextLine());
        System.out.println("출판일 입력(YYYY-MM-DD): ");
        book.setPublication_date(scan.nextLine());
        System.out.println("페이지수 입력: ");
        book.setNumber_of_pages(scan.nextInt()); scan.nextLine();
        System.out.println("카테고리 입력: ");
        book.setCategory(scan.nextLine());
        System.out.println("가격 입력: ");
        book.setPrice(scan.nextInt()); scan.nextLine();
        System.out.println("전자책 여부 입력(Y/N): ");
        book.setEbook(scan.nextLine().toUpperCase());

        int cnt = bdao.insertData(book);
        if (cnt == 1) {
            System.out.println("책이 등록 되었습니다.");
        } else {
            System.out.println("등록 실패");
        }
    }

    // 3. 책 수정 (ID 기준)
    public void updateData() {
        System.out.println("수정할 책 ID 입력: ");
        int id = scan.nextInt(); scan.nextLine();
        Book book = bdao.getOne(id);
        if (book == null) {
            System.out.println("책이 존재하지 않습니다.");
            return;
        }

        System.out.println("현재 정보: ");
        System.out.println(book.showBookInfo());
        System.out.println();

        System.out.println("제목 입력: ");
        book.setTitle(scan.nextLine());
        System.out.println("저자 입력: ");
        book.setAuthor(scan.nextLine());
        System.out.println("출판사 입력: ");
        book.setPublisher(scan.nextLine());
        System.out.println("출판일 입력(YYYY-MM-DD): ");
        book.setPublication_date(scan.nextLine());
        System.out.println("페이지수 입력: ");
        book.setNumber_of_pages(scan.nextInt()); scan.nextLine();
        System.out.println("카테고리 입력: ");
        book.setCategory(scan.nextLine());
        System.out.println("가격 입력: ");
        book.setPrice(scan.nextInt()); scan.nextLine();
        System.out.println("전자책 여부 입력(Y/N): ");
        book.setEbook(scan.nextLine().toUpperCase());

        int cnt = bdao.updateData(book);
        if (cnt == 1) System.out.println("책 정보가 수정되었습니다.");
        else System.out.println("수정 실패");
    }

    // 4. 책 삭제 (ID 기준)
    public void deleteData() {
        System.out.println("삭제할 책 ID 입력: ");
        int id = scan.nextInt(); scan.nextLine();

        // 삭제 전 해당 책이 존재하는지 확인
        Book book = bdao.getOne(id);
        if (book == null) {
            System.out.println("해당 ID의 책이 존재하지 않습니다.");
            return;
        }
        System.out.println("삭제할 책: " + book.getTitle());
        System.out.println("정말 삭제하시겠습니가? (Y/N): ");
        String confirm = scan.nextLine().toUpperCase();

        if (confirm.equals("Y")) {
            int cnt = bdao.deleteData(id);
            if (cnt == 1) System.out.println("책이 삭제 되었습니다.");
            else System.out.println("삭제 실패");
        } else {
            System.out.println("삭제를 취소하였습니다.");
        }
    }

    // 5. 전체 책 수 조회
    public void getSize() {
        int cnt = bdao.getSize();
        System.out.println("전체 책 수: " + cnt + "권");
    }

    // 6. 제목으로 책 조회(간단 조회)
    public void getOne() {
        System.out.println("조회할 책 제목 입력: ");
        String title = scan.nextLine().trim();


        if (title.isEmpty()) {
            System.out.println("제목을 입력해 주세요");
            return;
        }
        System.out.println("'" + title + "' 책 제목 검색 중...");
        List<Book> list = bdao.selectByTitle(title);
        if (list.isEmpty()){
            System.out.println("해당 제목의 책이 없습니다.");
        } else {
            System.out.println("검색 결과 (" + list.size() + "권)");
            System.out.println("ID\t제목\t저자\t출판사\t출판일\t페이지\t카테고리\t가격\t전자책");
            System.out.println("=".repeat(120));
            for (Book book : list) {
                System.out.println(book.showBookInfo());
            }
        }
    }

    // 7. 카테고리로 책 조회
    public void getBookByCategory() {
        System.out.println("조회할 카테고리 입력: ");
        String category = scan.nextLine().trim();

        if (category.isEmpty()) {
            System.out.println("카테고리를 입력해 주세요.");
            return;
        }
        System.out.println("'" + category + "' 카테고리 검색 중...");
        List<Book> list = bdao.selectByCategory(category);

        if (list.isEmpty()){
            System.out.println("해당 카테고리의 책이 없습니다.");
            System.out.println("참고: 정확한 카테고리명을 입력해 주세요.(예: 소설, 인문, 라이프스타일, 과학");
        } else {
            System.out.println("카테고리 '" + category + "' 검색 결과 (" + list.size() + "권)");
            System.out.println("ID\t제목\t저자\t출판사\t출판일\t페이지\t카테고리\t가격\t전자책");
            System.out.println("=".repeat(120));
            for (Book book : list) {
                System.out.println(book.showBookInfo());
            }
        }
    }

    // 8. ebook 존재 여부 확인
    public void checkBookExists() {
        System.out.println("확인할 책 ID 입력: ");
        int id = scan.nextInt();
        scan.nextLine();

    // 먼저 해당 ID의 책이 존재하는지 확인
        Book book = bdao.getOne(id);
        if (book == null) {
            System.out.println("해당 ID의 책이 존재하지 않습니다.");
            return;
        }
        System.out.println("책 제목: " + book.getTitle());
        boolean exists = bdao.existsById(id);

        if (exists) {
            System.out.println("해당 ID의 전자책이 존재합니다.");
        } else {
            System.out.println("해당 ID의 전자책이 존재하지 않습니다.(종이책만 있습니다.)");
        }
    }

    // 9. ID로 책 1권 조회
    public void getBookById() {
        System.out.println("조회할 책 ID 입력: ");
        int id = scan.nextInt();
        scan.nextLine();

        Book book = bdao.getOne(id);
        if (book == null) {
            System.out.println("해당 ID의 책이 없습니다.");
        } else {
            System.out.println("ID\t제목\t저자\t출판사\t출판일\t페이지\t카테고리\t가격\t전자책");
            System.out.println("=".repeat(120));
            System.out.println(book.showBookInfo());
        }
    }

    // 10. 제목으로 상세 정보 출력
    public void showBookInfoByTitle() {
        System.out.print("책 제목 입력: ");
        String title = scan.nextLine().trim();



        if (title.isEmpty()) {
            System.out.println("제목을 입력해 주세요.");
            return;
        }
        System.out.println("'" + title + "' 책 제목 검색 중...");
        List<Book> books = bdao.selectByTitle(title);

        if (books.isEmpty()){
            System.out.println("해당 제목의 책이 없습니다.");
        } else {
            System.out.println("검색 결과 (" + books.size() + "권)");
            System.out.println("=".repeat(50));
            for (int i = 0; i < books.size(); i++) {
                Book b = books.get(i);
                System.out.println("[" + (i+1) + "번째 책]");
                System.out.println("ID: " + b.getId());
                System.out.println("제목: " + b.getTitle());
                System.out.println("저자: " + b.getAuthor());
                System.out.println("출판사: " + b.getPublisher());
                System.out.println("출판일: " + b.getPublication_date());
                System.out.println("페이지 수: " + b.getNumber_of_pages());
                System.out.println("카테고리: " + b.getCategory());
                System.out.println("가격: " + b.getPrice() + "원");
                System.out.println("전자책: " + (b.getEbook().equalsIgnoreCase("Y") ? "있음" : "없음"));
                if (i < books.size() -1){
                System.out.println("-----------------------------");
            }
        }
            System.out.println("=".repeat(50));
    }
}


    // 11. 제목으로 전자책 여부만 출력
    public void checkEbookByTitle() {
        System.out.print("책 제목 입력: ");
        String title = scan.nextLine().trim();

        if (title.isEmpty()){
            System.out.println("제목을 입력해 주세요. ");
            return;
        }
        System.out.println("'" + title + "' 책 제목 검색 중...");

    // 먼저 해당 제목의 책이 있는지 확인
    List<Book> books = bdao.selectByTitle(title);
    if (books.isEmpty()){
        System.out.println("해당 제목의 책이 존재하지 않습니다.");
        return;
    }

        // 전자책 여주 확인
        boolean exists = bdao.existsEbookByTitle(title);

        System.out.println("검색된 책: " + books.size() + "권");
        if (exists) {
            System.out.println("해당 책은 전자책도 있습니다.");
        } else {
            System.out.println("해당 책은 전자책은 없습니다. (종이책만 있습니다)");
        }
    }

}


