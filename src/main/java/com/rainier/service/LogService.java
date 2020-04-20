package com.rainier.service;

import com.rainier.util.Result;

import java.text.ParseException;
import java.util.Map;

public interface LogService {
    Result selByPage(Map map) throws ParseException;
}
