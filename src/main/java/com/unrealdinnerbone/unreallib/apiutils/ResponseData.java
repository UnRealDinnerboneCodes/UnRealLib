package com.unrealdinnerbone.unreallib.apiutils;

import java.util.List;
import java.util.Map;

public record ResponseData<T>(T data, int statusCode, Map<String, List<String>> headers) {}
