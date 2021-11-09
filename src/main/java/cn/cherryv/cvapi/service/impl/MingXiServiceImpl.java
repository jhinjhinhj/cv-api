package cn.cherryv.cvapi.service.impl;

import cn.cherryv.cvapi.model.SaleMans;
import cn.cherryv.cvapi.model.Stroes;
import cn.cherryv.cvapi.model.WeiFa;
import cn.cherryv.cvapi.service.MingXiService;
import cn.cherryv.cvapi.mapper.MingXiMapper;
import cn.cherryv.cvapi.model.Mingxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MingXiServiceImpl implements MingXiService {

    @Autowired
    MingXiMapper mingXiMapper;

    @Autowired
    private JavaMailSender javaMailSender;


    //sheet1数据
    @Override
    public List<Mingxi> selectMingXi() {
        List<Mingxi> Mingxis = mingXiMapper.selectMingXi();
        if (moneyCheckOut(Mingxis)) {
            return Mingxis;
        }else {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("test");
            message.setFrom("18855097348@163.com");
            message.setTo("daixuanyu0810@gamil.com");
            //抄送人
            message.setCc("18855097348@163.com");

            //隐秘抄送人
            //message.setBcc();
            message.setSentDate(new Date());

            message.setText("金额不一致");
            javaMailSender.send(message);

        }
        return null;
    }

    //sheet2数据
    @Override
    public List<SaleMans> selectSaleMans() {
        List<SaleMans> saleMans = mingXiMapper.selectSaleMans();
        return saleMans;
    }

    //sheet3数据
    @Override
    public List<Stroes> selectStore() {
        List<Stroes> stroes = mingXiMapper.selectStore();

        setProportion(stroes);
        return stroes;
    }

    //sheet5数据
    @Override
    public List<WeiFa> selectWeiFa() {
        List<WeiFa> mingxis = mingXiMapper.selectWeiFa();
        return mingxis;
    }


    //校验查询结果中的红利金额是否和供应商余额表中当月发送扣除的余额是否相同

    public boolean moneyCheckOut(List<Mingxi> Mingxis) {
        long count = Mingxis.stream().mapToInt(Mingxi::getBonus).sum();
        return count == mingXiMapper.CountMoney() ? true : false;
    }


    //计算分店的红包占比

    public List<Stroes> setProportion(List<Stroes> lists) {
        HashMap<String, Integer> hashMap = groupStore(lists);

        for (String key : hashMap.keySet()) {
            for (Stroes list : lists) {
                if (key.equals(list.getBrand().substring(0, 2))) {
                    Integer integer = hashMap.get(key);
                    NumberFormat numberFormat = NumberFormat.getInstance();
                    numberFormat.setMaximumFractionDigits(2);
                    Integer salesProductCount = list.getSalesProductCount();
                    list.setProportion(numberFormat.format((double) salesProductCount / (double) integer * 100D) + "%");
                }
            }
        }

        return lists;

    }

    //根据店名归类

    public HashMap<String, Integer> groupStore(List<Stroes> lists) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < lists.size(); i++) {
            //店铺名称
            String brand = lists.get(i).getBrand().substring(0, 2);
            if (hashMap.get(brand) == null) {
                //将店铺名称和销售数量加入map中
                hashMap.put(brand, lists.get(i).getSalesProductCount());
            } else {
                hashMap.put(brand, hashMap.get(brand) + lists.get(i).getSalesProductCount());
            }
        }
        return hashMap;
    }

}
