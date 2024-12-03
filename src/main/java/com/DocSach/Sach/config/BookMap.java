package com.DocSach.Sach.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Builder
public class BookMap {
    public Map<Long, Long> bookDict = new HashMap<>();
    public BookMap(){
        bookDict.put(Long.parseLong("1"), Long.parseLong("0"));
        bookDict.put(Long.parseLong("2"), Long.parseLong("1"));
        bookDict.put(Long.parseLong("3"), Long.parseLong("2"));
        bookDict.put(Long.parseLong("4"), Long.parseLong("3"));
        bookDict.put(Long.parseLong("6"), Long.parseLong("4"));
        bookDict.put(Long.parseLong("8"), Long.parseLong("5"));
        bookDict.put(Long.parseLong("9"), Long.parseLong("6"));
        bookDict.put(Long.parseLong("10"), Long.parseLong("7"));
        bookDict.put(Long.parseLong("11"), Long.parseLong("8"));
        bookDict.put(Long.parseLong("13"), Long.parseLong("9"));
        bookDict.put(Long.parseLong("15"), Long.parseLong("10"));
    }
//    public Long get
}
