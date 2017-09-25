package com.seatech.ttsp.thamso;

import java.util.Collection;
import java.util.Iterator;

public class ThamSoHThong {
    public ThamSoHThong() {
    }

    public String getThamSoHThong(String strTenTSo,
                                  Collection collThamSoList) {
        Iterator iter = collThamSoList.iterator();
        ThamSoHThongVO tsVO = null;
        while (iter.hasNext()) {
            tsVO = (ThamSoHThongVO)iter.next();
            if (tsVO != null) {
                if (strTenTSo.equalsIgnoreCase(tsVO.getTen_ts())) {
                    return tsVO.getGiatri_ts();
                }
            }
        }
        return "";
    }

    public String getThamSoHThong(String strTenTSo, Long nKBacID,
                                  Collection collThamSoList) {
        Iterator iter = collThamSoList.iterator();
        ThamSoHThongVO tsVO = null;
        while (iter.hasNext()) {
            tsVO = (ThamSoHThongVO)iter.next();
            if (tsVO != null) {
                if (strTenTSo.equalsIgnoreCase(tsVO.getTen_ts()) &&
                    nKBacID.longValue() == tsVO.getKb_id().longValue()) {
                    return tsVO.getGiatri_ts();
                }
            }
        }
        return "";
    }
}
