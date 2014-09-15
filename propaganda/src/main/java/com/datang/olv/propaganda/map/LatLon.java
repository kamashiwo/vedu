package com.datang.olv.propaganda.map;

import com.baidu.mapapi.model.LatLng;

/**
 * Created by xqjiaoshi02 on 14-8-5.
 */
public final class LatLon {

    LatLng ll = null;
    public final double latitude;
    public final double longitude;

    public LatLon(double latitude, double longitude) {
        ll = new LatLng(latitude, longitude);
        this.longitude = ll.longitude;
        this.latitude = ll.latitude;
    }

    public LatLon(LatLng latLng) {
        this(latLng.latitude, latLng.longitude);
    }

    public LatLng latLng() {
        return ll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LatLon latLon = (LatLon) o;

        if (Double.compare(latLon.latitude, latitude) != 0) return false;
        if (Double.compare(latLon.longitude, longitude) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return ll.toString();
    }

}
