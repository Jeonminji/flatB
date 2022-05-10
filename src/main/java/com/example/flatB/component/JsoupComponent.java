package com.example.flatB.component;

import com.example.flatB.domain.dto.CompareMusicDto;
import com.example.flatB.domain.dto.CompareOttDto;
import com.example.flatB.repository.CompareMusicRepository;
import com.example.flatB.repository.CompareOttRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JsoupComponent { //크롤링
    private final CompareOttRepository compareOttRepository;
    private final CompareMusicRepository compareMusicRepository;

    /* OTT */
    @Transactional
    public void getNetflix() { //넷플릭스
        final String netfilxUrl = "https://help.netflix.com/ko/node/24926";
        Connection conn = Jsoup.connect(netfilxUrl);
        try {
            Document document = conn.get();
            getNetflix(document);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    public void getNetflix(Document document) {
        if(!compareOttRepository.existsByOttName("Netflix")) { //db에 없으면 저장
            Elements elements1 = document.select("table.c-table thead tr th");
            Elements elements2 = document.select("table.c-table tbody tr");
            for (int i = 0; i < elements1.size(); i++) { //0~3열
                if (i == 3) break;
                CompareOttDto compareOttDto = CompareOttDto.builder().build();
                StringBuffer etc = new StringBuffer();
                StringBuffer quality = new StringBuffer();
                for (int j = 0; j < elements2.size(); j++) { //1~7행
                    Element content = elements2.get(j);
                    Elements tdContents = content.select("td");
                    compareOttDto.setOttName("Netflix");
                    if (j == 0) {
                        compareOttDto.setOttPlan(elements1.get(i + 1).text()); //요금제명
                        compareOttDto.setOttPrice(tdContents.get(i + 1).text()); //요금제가격
                    } else if (j == 1) {
                        compareOttDto.setOttPlaybacknum(tdContents.get(i + 1).text()); //동시재생수
                    } else if (j == 5) {
                        final String HDQuality = getHDQuality(tdContents.get(i + 1).text()); //HD 화질 이용 가능
                        quality.append(HDQuality + "+");
                    } else if (j == 6) {
                        final String UHDQuality = getUHDQuality(tdContents.get(i + 1).text()); //UHD 화질 이용 가능
                        quality.append(UHDQuality);
                    } else {
                        etc.append(tdContents.get(0).text() + ": " + tdContents.get(i + 1).text() + "/");
                    }
                }
                compareOttDto.setOttQuality(quality.toString());
                compareOttDto.setOttEtc(etc.toString());
                compareOttRepository.save(compareOttDto.toEntity());
            }
        }
    }

    public String getHDQuality(String quality) {
        if(quality.equals(""))
            return "No HD";
        else
            return "HD";
    }

    public String getUHDQuality(String quality) {
        if(quality.equals(""))
            return "No UHD";
        else
            return "UHD";
    }

    public void getWavve() { //웨이브 동적크롤링 필요...
        final String wavveUrl = "https://www.wavve.com/voucher";
        Connection conn = Jsoup.connect(wavveUrl);
        try {
            Document document = conn.get();
            getWavve(document);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    public void getWavve(Document document) {
        System.out.println("웨이브 크롤링");
        //System.out.println(document);
        Elements elements = document.select("ul.product-group li.ticket-group-list span.product-name-box");
        //System.out.println(elements);

        /*for(int i = 0; i < elements.size(); i++) {
            Element e = elements.get(i);
            Elements content = e.select("span");
            System.out.println(content);
            System.out.println(e.select("p").text());
        }*/

    }

    /* Music */
    @Transactional
    public void getMelon() { //멜론 크롤링
        final String melonUrl = "https://www.melon.com/buy/pamphlet/discount.htm";
        Connection conn = Jsoup.connect(melonUrl);
        try {
            Document document = conn.get();
            getMelon(document);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    public void getMelon(Document document) {
        if(!compareMusicRepository.existsByMusicName("Melon")) { //db에 없으면 저장
            Elements elements = document.select("div.product_purchase");
            for (Element content : elements) {
                Elements title = content.select("h4.title");
                Elements description = content.select("p.text");
                Elements salePrice = content.select("span.sale_price");
                Elements normalPrice = content.select("span.normal_price");

                CompareMusicDto compareMusicDto = CompareMusicDto.builder().build();
                compareMusicDto.setMusicName("Melon");
                compareMusicDto.setMusicPlan(title.text().replaceAll("이용권 자세히 알기", "")); //요금제명
                compareMusicDto.setMusicPlandescription(description.text()); //요금제 설명
                compareMusicDto.setMusicDiscount(salePrice.text()); //할인가격
                compareMusicDto.setMusicPrice(normalPrice.text()); //기본가격
                compareMusicRepository.save(compareMusicDto.toEntity());
            }
        }
    }

    @Transactional
    public void getGenie() { //지니 크롤링
        final String genieUrl = "https://www.genie.co.kr/buy/recommend";
        Connection conn = Jsoup.connect(genieUrl);
        try {
            Document document = conn.get();
            getGenie(document);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    public void getGenie(Document document) {
        if(!compareMusicRepository.existsByMusicName("Genie")) { //db에 없으면 저장
            Elements elements = document.select("div.payment div.divide");
            for (Element content : elements) {
                CompareMusicDto compareMusicDto = CompareMusicDto.builder().build();

                Elements title = content.select("h4");
                Elements descriptions = content.select("dl dt");
                Elements normal_prices = content.select("dl dd del");
                Elements sale_prices = content.select("dl dd strong.price");

                for(int i = 0; i < descriptions.size(); i++) {
                    Element des = descriptions.get(i);
                    compareMusicDto.setMusicName("Genie");
                    compareMusicDto.setMusicPlan(title.text()); //요금제명
                    compareMusicDto.setMusicPlandescription(des.text()); //요금제 설명
                    if (i < normal_prices.size()) {
                        compareMusicDto.setMusicPrice(normal_prices.get(i).text()); //기본가격
                        compareMusicDto.setMusicDiscount(sale_prices.get(i).text() + "원"); //할인가격
                    } else
                        compareMusicDto.setMusicPrice(sale_prices.get(i).text() + "원"); //기본가격

                    compareMusicRepository.save(compareMusicDto.toEntity());
                }
            }
        }
    }

    @Transactional
    public void getSpotify() { //스포티파이 크롤링
        final String spotifyUrl = "https://www.spotify.com/kr-ko/premium/#plans";
        Connection conn = Jsoup.connect(spotifyUrl);
        try {
            Document document = conn.get();
            getSpotify(document);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    public void getSpotify(Document document) {
        if (!compareMusicRepository.existsByMusicName("Spotify")) { //db에 없으면 저장
            Elements elements = document.select("div.sc-gBsxbr");
            Elements contents = elements.select(" div[data-current-plan-text]");
            Elements etc = elements.select("ul.sc-kGVuwA li.sc-ezjrSx p.sc-kBqmDu");

            for(Element content : contents) {
                StringBuffer etcBuffer = new StringBuffer();
                CompareMusicDto compareMusicDto = CompareMusicDto.builder().build();


                Elements title = content.select("h3");
                Elements description = content.select("ul.sc-gggoXN li.sc-crzoAE span");
                Elements price_etc = content.select("p.sc-irqbAE");

                compareMusicDto.setMusicName("Spotify");
                compareMusicDto.setMusicPlan(title.text()); //요금제명
                compareMusicDto.setMusicPlandescription(description.text()); //요금제 설명
                compareMusicDto.setMusicPrice(price_etc.get(0).text()); //가격
                etcBuffer.append(price_etc.get(1).text() + "/"); //기타
                for (int i = 0; i < etc.size(); i++) {
                    etcBuffer.append(etc.get(i).text() + "/"); //기타
                }
                compareMusicDto.setMusicEtc(etcBuffer.toString());

                compareMusicRepository.save(compareMusicDto.toEntity());
            }
        }
    }

    @Transactional
    public void getBugs() { //벅스 크롤링
        final String bugsUrl = "https://music.bugs.co.kr/pay/public";
        Connection conn = Jsoup.connect(bugsUrl);
        try {
            Document document = conn.get();
            getBugs(document);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    public void getBugs(Document document) {
        if(!compareMusicRepository.existsByMusicName("Bugs")) { //db에 없으면 저장
            Elements elements = document.select("div.innerContainer");
            Elements plans = elements.select("div.itemTitle");
            Elements prices = elements.select("ul.price li.day30 strong");

            for (int i = 0; i < plans.size(); i++) {
                Element content = plans.get(i);
                CompareMusicDto compareMusicDto = CompareMusicDto.builder().build();

                Elements title = content.select("div.itemTitle strong");
                Elements description = content.select("div.itemTitle ul li");
                Element price = prices.get(i);

                compareMusicDto.setMusicName("Bugs");
                compareMusicDto.setMusicPlan(title.text()); //요금제명
                compareMusicDto.setMusicPlandescription(description.get(0).text()); //요금제 설명
                compareMusicDto.setMusicPrice(price.text()); //가격

                compareMusicRepository.save(compareMusicDto.toEntity());
            }
        }
    }
}
