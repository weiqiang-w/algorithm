package utils;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
    public static <T> List<T> paging(List<T> list, Integer pageNum, Integer pageSize) {
        if (list == null) {
            return new ArrayList<>();
        }
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        int start = (pageNum - 1) * pageSize;
        int end = pageNum * pageSize;
        int size = list.size();
        List<T> pageList;
        if (end <= size) {
            pageList = new ArrayList<>(list.subList(start, end));
        } else if (start >= size) {
            pageList = new ArrayList<>();
        } else {
            end = list.size();
            pageList = new ArrayList<>(list.subList(start, end));
        }
        return pageList;
    }
}
