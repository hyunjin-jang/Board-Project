package hyun.portfolio9.entities.dto;

import lombok.Data;

@Data
public class PostPagingDto {
    private int page;       // 현재 페이지 번호
    private int size;       // 페이지당 출력할 데이터 개수
}
