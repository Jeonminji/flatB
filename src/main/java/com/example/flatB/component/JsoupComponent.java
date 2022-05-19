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
//        final String wavveUrl = "https://www.wavve.com/voucher";
//        Connection conn = Jsoup.connect(wavveUrl);
//        try {
//            Document document = conn.get();
//            getWavve(document);
//        } catch (IOException ignored) {
//            ignored.printStackTrace();
//        }
        String[] ottPlan = {"Basic", "Standard", "Premium"};
        String[] ottPrice = {"7,900원", "10,900원", "13,900원"};
        String[] ottQuality = {"HD화질", "FHD화질", "최상위화질"};
        String[] ottPlaybacknum = {"1", "2", "4"};

        if(!compareOttRepository.existsByOttName("Wavve")) {
            for (int i = 0; i < ottPlan.length; i++) {
                CompareOttDto compareOttDto = CompareOttDto.builder().build();
                compareOttDto.setOttName("Wavve");
                compareOttDto.setOttPlan(ottPlan[i]);
                compareOttDto.setOttPrice(ottPrice[i]);
                compareOttDto.setOttQuality(ottQuality[i]);
                compareOttDto.setOttPlaybacknum(ottPlaybacknum[i]);
                compareOttRepository.save(compareOttDto.toEntity());
            }
        }
    }

//    public void getWavve(Document document) {
//        System.out.println("웨이브 크롤링");
//        //System.out.println(document);
//        Elements elements = document.select("ul.product-group li.ticket-group-list span.product-name-box");
//        //System.out.println(elements);
//
//        /*for(int i = 0; i < elements.size(); i++) {
//            Element e = elements.get(i);
//            Elements content = e.select("span");
//            System.out.println(content);
//            System.out.println(e.select("p").text());
//        }*/
//
//    }

    @Transactional
    public void getTving() { //티빙
//        final String tvingUrl = "";
//        Connection conn = Jsoup.connect(tvingUrl);
//        try {
//            Document document = conn.get();
//            getTving(document);
//        } catch (IOException ignored) {
//            ignored.printStackTrace();
//        }

        String[] ottPlan = {"베이직", "스탠다드", "프리미엄"};
        String[] ottPrice = {"7,900원", "10,900원", "13,900원"};
        String[] ottQuality = {"720p HD", "1080p FHD", "1080p FHD + 4K"};
        String[] ottPlaybacknum = {"1", "2", "4"};

        if(!compareOttRepository.existsByOttName("Tving")) { //db에 없으면 저장
            for (int i = 0; i < ottPlan.length; i++) {
                CompareOttDto compareOttDto = CompareOttDto.builder().build();
                compareOttDto.setOttName("Tving");
                compareOttDto.setOttPlan(ottPlan[i]);
                compareOttDto.setOttPrice(ottPrice[i]);
                compareOttDto.setOttQuality(ottQuality[i]);
                compareOttDto.setOttPlaybacknum(ottPlaybacknum[i]);
                compareOttRepository.save(compareOttDto.toEntity());
            }
        }
    }

    @Transactional
    public void getWatcha() { //왓챠
//        final String watchaUrl = "";
//        Connection conn = Jsoup.connect(watchaUrl);
//        try {
//            Document document = conn.get();
//            getWatcha(document);
//        } catch (IOException ignored) {
//            ignored.printStackTrace();
//        }
        String[] ottPlan = {"베이직", "프리미엄"};
        String[] ottPrice = {"7,900원", "12,900원"};
        String[] ottQuality = {"Full HD", "Ultra HD 4K"};
        String[] ottPlaybacknum = {"1", "4"};

        if(!compareOttRepository.existsByOttName("Watcha")) { //db에 없으면 저장
            for (int i = 0; i < ottPlan.length; i++) {
                CompareOttDto compareOttDto = CompareOttDto.builder().build();
                compareOttDto.setOttName("Watcha");
                compareOttDto.setOttPlan(ottPlan[i]);
                compareOttDto.setOttPrice(ottPrice[i]);
                compareOttDto.setOttQuality(ottQuality[i]);
                compareOttDto.setOttPlaybacknum(ottPlaybacknum[i]);
                compareOttRepository.save(compareOttDto.toEntity());
            }
        }
    }

    @Transactional
    public void getDisneyplus() { //디즈니플러스
//        final String disneyplusUrl = "";
//        Connection conn = Jsoup.connect(disneyplusUrl);
//        try {
//            Document document = conn.get();
//            getDisneyplus(document);
//        } catch (IOException ignored) {
//            ignored.printStackTrace();
//        }

        String[] ottPlan = {"월 9,900원", "연 99,000원"};
        String[] ottPrice = {"9,900원", "99,900원"};
        String[] ottQuality = {"4K UHD 및 HDR", "4K UHD 및 HDR"};
        String[] ottPlaybacknum = {"4", "4"};

        if(!compareOttRepository.existsByOttName("Disneyplus")) { //db에 없으면 저장
            for (int i = 0; i < ottPlan.length; i++) {
                CompareOttDto compareOttDto = CompareOttDto.builder().build();
                compareOttDto.setOttName("Disneyplus");
                compareOttDto.setOttPlan(ottPlan[i]);
                compareOttDto.setOttPrice(ottPrice[i]);
                compareOttDto.setOttQuality(ottQuality[i]);
                compareOttDto.setOttPlaybacknum(ottPlaybacknum[i]);
                compareOttRepository.save(compareOttDto.toEntity());
            }
        }
    }

    @Transactional
    public void getLaftel() { //라프텔
//        final String laftelUrl = "";
//        Connection conn = Jsoup.connect(laftelUrl);
//        try {
//            Document document = conn.get();
//            getLaftel(document);
//        } catch (IOException ignored) {
//            ignored.printStackTrace();
//        }

        String[] ottPlan = {"베이직", "프리미엄"};
        String[] ottPrice = {"9,900원", "14,900원"};
        String[] ottQuality = {"FHD", "FHD"};
        String[] ottPlaybacknum = {"1", "4"};

        if(!compareOttRepository.existsByOttName("Laftel")) { //db에 없으면 저장
            for (int i = 0; i < ottPlan.length; i++) {
                CompareOttDto compareOttDto = CompareOttDto.builder().build();
                compareOttDto.setOttName("Laftel");
                compareOttDto.setOttPlan(ottPlan[i]);
                compareOttDto.setOttPrice(ottPrice[i]);
                compareOttDto.setOttQuality(ottQuality[i]);
                compareOttDto.setOttPlaybacknum(ottPlaybacknum[i]);
                compareOttRepository.save(compareOttDto.toEntity());
            }
        }
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

    @Transactional
    public void getFlo() { //플로
//        final String floUrl = "https://www.music-flo.com/purchase/voucher";
//        Connection conn = Jsoup.connect(floUrl);
//        try {
//            Document document = conn.get();
//            getFlo(document);
//        } catch (IOException ignored) {
//            ignored.printStackTrace();
//        }
        String[] musicPlan = {"무제한 듣기+오프라인 재생", "무제한 듣기+오프라인 재생", "무제한 듣기+오프라인 재생",
        "무제한 듣기", "무제한 듣기", "무제한 듣기", "300회 듣기", "모바일 무제한 듣기", "모바일 무제한 듣기", "모바일 무제한 듣기"};
        String[] musicPlandescription = {"기기제한 없음, 무제한 스트리밍, 오프라인 재생",
                "기기제한 없음, 무제한 스트리밍, 오프라인 재생", "기기제한 없음, 무제한 스트리밍, 오프라인 재생",
        "기기제한 없음, 무제한 듣기", "기기제한 없음, 무제한 듣기", "기기제한 없음, 무제한 듣기", "기기제한 없음, 횟수 제한",
        "모바일 전용, 무제한 스트리밍", "모바일 전용, 무제한 스트리밍", "모바일 전용, 무제한 스트리밍"};
        String[] musicPrice = {"정기결제 11,000원", "T멤버십 최소 100원 6개월 10,900원", "1개월권 11,000원",
                "정기결제 8,000원", "T멤버십 최소 100원 6개월 7,900원", "1개월권 8,000원", "1개월권 4,800원",
                "정기결제 7,000원", "T멤버십 최소 100원 6개월 6,900원", "1개월권 7,000원"};
        String[] musicDiscount = {"10,900원", "100원", "", "7,900원", "100원", "", "", "6,900원", "100원", ""};

        if(!compareMusicRepository.existsByMusicName("Flo")) { //db에 없으면 저장
            for(int i = 0; i< musicPlan.length; i++) {
                CompareMusicDto compareMusicDto = CompareMusicDto.builder().build();
                compareMusicDto.setMusicName("Flo");
                compareMusicDto.setMusicPlan(musicPlan[i]);
                compareMusicDto.setMusicPlandescription(musicPlandescription[i]);
                compareMusicDto.setMusicPrice(musicPrice[i]);
                compareMusicDto.setMusicDiscount(musicDiscount[i]);
                compareMusicRepository.save(compareMusicDto.toEntity());
            }
        }
    }

    @Transactional
    public void getYoutubeMusic() { //유튜브뮤직
//       final String youtubeMusicUrl = "";
//        Connection conn = Jsoup.connect(youtubeMusicUrl);
//        try {
//            Document document = conn.get();
//            getYoutubeMusic(document);
//        } catch (IOException ignored) {
//            ignored.printStackTrace();
//        }
        String[] musicPlan = {"월 8,690원"};
        String[] musicPlandescription = {"1개월 무료체험"};
        String[] musicPrice = {"8,690원"};
        String[] musicDiscount = {""};

        if(!compareMusicRepository.existsByMusicName("YoutubeMusic")) { //db에 없으면 저장
            for(int i = 0; i< musicPlan.length; i++) {
                CompareMusicDto compareMusicDto = CompareMusicDto.builder().build();
                compareMusicDto.setMusicName("YoutubeMusic");
                compareMusicDto.setMusicPlan(musicPlan[i]);
                compareMusicDto.setMusicPlandescription(musicPlandescription[i]);
                compareMusicDto.setMusicPrice(musicPrice[i]);
                compareMusicDto.setMusicDiscount(musicDiscount[i]);
                compareMusicRepository.save(compareMusicDto.toEntity());
            }
        }
    }
}
