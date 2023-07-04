package com.ordersystem.demo.service;

import java.io.IOException;
import java.util.List;

public interface GoogleApiService {
    public int getRealDistance(List<String> origin, List<String> destination) throws IOException;
}
