package com.example.flatB.component;

import com.example.flatB.domain.dto.CompareMusicDto;
import com.example.flatB.domain.dto.CompareOttDto;
import com.example.flatB.repository.CompareMusicRepository;
import com.example.flatB.repository.CompareOttRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JsoupComponent { //크롤링
    private final CompareOttRepository compareOttRepository;
    private final CompareMusicRepository compareMusicRepository;
    private WebDriver driver;

    //크롤링에 필요한 로그인 아이디, 비밀번호
    @Value("${tving.id}")
    private String tvingId;
    @Value("${tving.pwd}")
    private String tvingPwd;

    @Value("${watcha.id}")
    private String watchaId;
    @Value("${watcha.pwd}")
    private String watchaPwd;

    @Value("${laftel.id}")
    private String laftelId;
    @Value("${laftel.pwd}")
    private String laftelPwd;


    public void setup() { //셀레니움 설정
        Path path = Paths.get("/usr/bin/chromedriver"); //리눅스에서는 /usr/bin/chromedriver로 수정
        System.setProperty("webdriver.chrome.driver", path.toString()); //크롬 드라이버 셋팅 (드라이버 설치한 경로 입력)

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); //크롬창안띄움
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-popup-blocking"); // 팝업 무시
        options.addArguments("--disable-default-apps"); // 기본앱 사용안함
        options.addArguments("--disable-gpu"); //GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        options.addArguments("--no-sandbox"); // sandbox 프로세스 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.

        try {
            driver = new ChromeDriver(options);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

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
                    }
                }
                compareOttDto.setOttQuality(quality.toString());
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

    @Transactional
    public void getWavve() { //웨이브
        if(!compareOttRepository.existsByOttName("Wavve")) { //db에 없으면 저장
            setup();

            try {
                final String url = "https://www.wavve.com/voucher/index.html";

                driver.get(url);    //브라우저에서 url로 이동한다.
                Thread.sleep(1000); //브라우저 로딩될때까지 잠시 기다린다.

                for(int i = 2; i < 5; i++) {
                    WebElement plan = driver.findElement(By.xpath("//*[@id=\"contents\"]/div[2]/div/div[2]/div/div[1]/section/table/thead/tr/th[" + i + "]/div/h2"));
                    WebElement price = driver.findElement(By.xpath("//*[@id=\"contents\"]/div[2]/div/div[2]/div/div[1]/section/table/thead/tr/th[" + i + "]/div/button"));
                    WebElement quality = driver.findElement(By.xpath("//*[@id=\"contents\"]/div[2]/div/div[2]/div/div[1]/section/table/tbody/tr[3]/td[" + (i-1) + "]/span"));
                    WebElement playbacknum = driver.findElement(By.xpath("//*[@id=\"contents\"]/div[2]/div/div[2]/div/div[1]/section/table/tbody/tr[4]/td[" + (i-1) + "]/span"));
                    CompareOttDto compareOttDto = CompareOttDto.builder().build();
                    compareOttDto.setOttName("Wavve");
                    compareOttDto.setOttPlan(plan.getText());
                    compareOttDto.setOttPlaybacknum(playbacknum.getText());
                    compareOttDto.setOttQuality(quality.getText());
                    compareOttDto.setOttPrice(price.getText());
                    compareOttRepository.save(compareOttDto.toEntity());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            down();
        }
    }

    @Transactional
    public void getTving() { //티빙
        if(!compareOttRepository.existsByOttName("Tving")) { //db에 없으면 저장
            setup();

            try {
                final String url = "https://user.tving.com/pc/user/otherLogin.tving?loginType=20&from=pc&rtUrl=https%3A%2F%2Fwww.tving.com&csite=&isAuto=false";

                //login 페이지
                driver.get(url);    //브라우저에서 url로 이동한다.
                Thread.sleep(1000); //브라우저 로딩될때까지 잠시 기다린다.

                driver.findElement(By.xpath("//*[@id=\"a\"]")).sendKeys(tvingId);
                driver.findElement(By.xpath("//*[@id=\"b\"]")).sendKeys(tvingPwd);
                driver.findElement(By.xpath("//*[@id=\"doLoginBtn\"]")).click();
                System.out.println("로그인 성공 = " + driver.getCurrentUrl());

                driver.navigate().to("https://www.tving.com/"); //메인으로 이동
                System.out.println("메인 = " + driver.getCurrentUrl());
                driver.navigate().to("https://www.tving.com/membership/tving"); //이용권 페이지로 이동
                System.out.println("이용권 = " + driver.getCurrentUrl());

                Thread.sleep(1000);
                for (int i = 2; i < 5; i++) {
                    CompareOttDto compareOttDto = CompareOttDto.builder().build();

                    WebElement plan = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/section/section/section[2]/section[2]/table/thead/tr/th[" + i + "]")); //요금제명
                    WebElement playbacknum = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/section/section/section[2]/section[2]/table/tbody/tr[2]/th[" + i + "]")); //동시재생수
                    WebElement quality = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/section/section/section[2]/section[2]/table/tbody/tr[3]/th[" + i + "]")); //화질
                    WebElement price = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/section/section/section[2]/section[2]/table/tbody/tr[7]/th[" + i + "]")); //가격

                    compareOttDto.setOttName("Tving");
                    compareOttDto.setOttPlan(plan.getText());
                    compareOttDto.setOttPrice(price.getText());
                    compareOttDto.setOttQuality(quality.getText());
                    compareOttDto.setOttPlaybacknum(playbacknum.getText());
                    compareOttRepository.save(compareOttDto.toEntity());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            down();
        }
    }

    @Transactional
    public void getWatcha() { //왓챠
        if (!compareOttRepository.existsByOttName("Watcha")) { //db에 없으면 저장
            setup();

            try {
                final String url = "https://watcha.com/sign_in";

                //login 페이지
                driver.get(url);    //브라우저에서 url로 이동한다.
                Thread.sleep(1000); //브라우저 로딩될때까지 잠시 기다린다.

                driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/main/div[1]/main/div/form/div[1]/input")).sendKeys(watchaId);
                driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/main/div[1]/main/div/form/div[2]/input")).sendKeys(watchaPwd);
                driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/main/div[1]/main/div/form/div[3]/button")).click();
                System.out.println("로그인 성공 = " + driver.getCurrentUrl());

                Thread.sleep(5000);
                driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/main/div[1]/section/ul/li[1]/button")).click();
                System.out.println("프로필 = " + driver.getCurrentUrl());

                Thread.sleep(5000);
                driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/header[1]/div/button")).click(); //이용권 선택
                System.out.println("이용권 = " + driver.getCurrentUrl());

                Thread.sleep(5000);
                List<WebElement> plans = driver.findElements(By.className("css-1afiy62")); //요금제명
                WebElement price1 = driver.findElement(By.cssSelector("li.css-4p3s42 > div.css-0")); //가격
                WebElement playbacknum1 = driver.findElements(By.className("css-4p3s42")).get(1); //동시재생수
                WebElement quality1 = driver.findElements(By.className("css-4p3s42")).get(2); //화질

                WebElement price2 = driver.findElement(By.cssSelector("li.css-g063x9 > div.css-0"));
                WebElement playbacknum2 = driver.findElements(By.className("css-g063x9")).get(1);
                WebElement quality2 = driver.findElements(By.className("css-g063x9")).get(2);

                for (int i = 0; i < 2; i++) {
                    CompareOttDto compareOttDto = CompareOttDto.builder().build();
                    compareOttDto.setOttName("Watcha");
                    if (i == 0) {
                        compareOttDto.setOttPlan(plans.get(0).getText());
                        compareOttDto.setOttPrice(price1.getText());
                        compareOttDto.setOttQuality(quality1.getText());
                        compareOttDto.setOttPlaybacknum(playbacknum1.getText());
                    } else {
                        compareOttDto.setOttPlan(plans.get(1).getText());
                        compareOttDto.setOttPrice(price2.getText());
                        compareOttDto.setOttQuality(quality2.getText());
                        compareOttDto.setOttPlaybacknum(playbacknum2.getText());
                    }
                    compareOttRepository.save(compareOttDto.toEntity());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            down();
        }
    }

    @Transactional
    public void getDisneyplus() { //디즈니플러스
        final String disneyplusUrl = "https://www.disneyplus.com/ko-kr/welcome/stream-now?cid=DSS-Search-Google-71700000088460638-&s_kwcid=AL%218468%213%21584589737503%21p%21%21g%21%21%EB%94%94%EC%A6%88%EB%8B%88&gclid=Cj0KCQjw1tGUBhDXARIsAIJx01kTAxMQtYajZeApxapQ76u5-V2OFTTwNuf2eJPuCPozituDP85vqmkaAqvZEALw_wcB&gclsrc=aw.ds";
        Connection conn = Jsoup.connect(disneyplusUrl);
        try {
            Document document = conn.get();
            getDisneyplus(document);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    public void getDisneyplus(Document document) {
        if(!compareOttRepository.existsByOttName("Disneyplus")) { //db에 없으면 저장
            Elements elements = document.select("li.grid-item h3");
            Elements elements1 = document.select("li.grid-item h4");
            Elements elements2 = document.select("li.grid-item p.medium");

            String quality = elements2.eq(2).text();
            if(quality.contains("화질"))
                quality = "4K UHD 및 HDR";

            for (int i = 0; i < 2; i++) {
                CompareOttDto compareOttDto = CompareOttDto.builder().build();
                compareOttDto.setOttName("Disneyplus");
                compareOttDto.setOttPrice(elements.eq(i).text()); //요금제 가격
                compareOttDto.setOttQuality(quality); //화질
                compareOttDto.setOttPlaybacknum(elements1.eq(1).text().substring(3, 4)); //동시재생수
                compareOttRepository.save(compareOttDto.toEntity());
            }
        }
    }

    @Transactional
    public void getLaftel() { //라프텔
        if (!compareOttRepository.existsByOttName("Laftel")) { //db에 없으면 저장
            setup();

            try {
                final String url = "https://laftel.net/auth/email";

                //login 페이지
                driver.get(url); //브라우저에서 url로 이동한다.
                Thread.sleep(1000); //브라우저 로딩될때까지 잠시 기다린다.

                driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/div/form/div[1]/div/input")).sendKeys(laftelId);
                driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/div/form/div[2]/div/input")).sendKeys(laftelPwd);
                driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/div/form/button")).click();
                System.out.println("로그인 성공 = " + driver.getCurrentUrl());

                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/div/div[2]/div[1]")).click(); //프로필 선택
                System.out.println("프로필 = " + driver.getCurrentUrl());

                WebDriverWait wait = new WebDriverWait(driver, 5); //브라우저 로딩될때까지 잠시 기다린다.
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/div/div/div[2]/a[4]")));

                driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/div/div/div[2]/a[4]")).click(); //멤버십 페이지로 이동
                System.out.println("이용권 = " + driver.getCurrentUrl());

                Thread.sleep(5000);
                for (int i = 1; i < 3; i++) {
                    CompareOttDto compareOttDto = CompareOttDto.builder().build();

                    WebElement plans = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/main/div[1]/div[" + i + "]/h2")); //요금제명
                    String plan = String.join(" ", plans.getText().split("\n"));
                    WebElement price = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/main/div[1]/div[" + i + "]/div[1]/span")); //가격
                    WebElement etc = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/main/div[1]/div[" + i + "]/div[1]/div/span")); //기타
                    WebElement playbacknum = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/main/div[1]/div[" + i + "]/div[2]/div[2]/span")); //동시재생수
                    WebElement quality = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/main/div[1]/div[" + i + "]/div[2]/div[5]/span")); //화질

                    compareOttDto.setOttName("Laftel");
                    compareOttDto.setOttPlan(plan);
                    compareOttDto.setOttPrice(price.getText());
                    compareOttDto.setOttQuality(quality.getText());
                    compareOttDto.setOttPlaybacknum(playbacknum.getText());
                    compareOttDto.setOttEtc(etc.getText());
                    compareOttRepository.save(compareOttDto.toEntity());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            down();
        }
    }

    /* Music */
    @Transactional
    public void getMelon() { //멜론
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
    public void getGenie() { //지니
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
    public void getSpotify() { //스포티파이
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
    public void getBugs() { //벅스
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
        final String floUrl = "https://www.music-flo.com/api/purchase/v1/general";
        Connection conn = Jsoup.connect(floUrl);
        try {
            Document document = conn
                    .header("origin", "https://www.music-flo.com/") // same-origin-polycy 로 인한 설정
                    .header("referer", "https://www.music-flo.com/") // same-origin-polycy 로 인한 설정
                    .ignoreContentType(true) // json 받아오려면 타입무시
                    .get();
            getFlo(document);
        } catch (IOException | ParseException ignored) {
            ignored.printStackTrace();
        }
    }

    public void getFlo(Document document) throws ParseException {
        if(!compareMusicRepository.existsByMusicName("Flo")) { //db에 없으면 저장
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(document.text());
            JSONObject jsonObject1 = (JSONObject) jsonObject.get("data");
            JSONObject jsonObject2 = (JSONObject) jsonObject1.get("nuguVoucher");
            JSONObject jsonObject3  = (JSONObject) jsonObject1.get("unlimitedVoucher");

            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject2.get("drmVoucher"));
            jsonArray.add(jsonObject2.get("unlimitedVoucher"));
            jsonArray.add(jsonObject2.get("quantityVoucher"));
            jsonArray.add(jsonObject3.get("unlimitedVoucher"));

            for(int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                CompareMusicDto compareMusicDto = CompareMusicDto.builder().build();
                compareMusicDto.setMusicName("Flo");
                JSONArray jsonArray1 = (JSONArray) object.get("voucherList");
                for(Object obj : jsonArray1) {
                    compareMusicDto.setMusicPlan(object.get("voucherCategoryName").toString()); //요금제명
                    compareMusicDto.setMusicPlandescription(object.get("voucherCategorySubName").toString()); //요금제 설명

                    JSONObject childObj = (JSONObject) obj;
                    int normalPrice = Integer.parseInt(childObj.get("normalPrice").toString()); //기본가격
                    int disCountPrice = Integer.parseInt(childObj.get("discountPrice").toString());
                    int disCount = normalPrice - disCountPrice; //할인가격
                    compareMusicDto.setMusicPrice(childObj.get("voucherName").toString() + " " + normalPrice + "원");
                    if (disCountPrice != 0) {
                        compareMusicDto.setMusicDiscount(disCount + "원");
                    }
                    compareMusicRepository.save(compareMusicDto.toEntity());
                }
            }
        }
    }

    @Transactional
    public void getYoutubeMusic() { //유튜브뮤직
        if(!compareMusicRepository.existsByMusicName("YoutubeMusic")) { //db에 없으면 저장
            setup();

            try {
                final String url = "https://www.youtube.com/musicpremium";

                driver.get(url); //브라우저에서 url로 이동한다.
                Thread.sleep(1000); //브라우저 로딩될때까지 잠시 기다린다.

                WebElement elements = driver.findElement(By.xpath("//*[@id=\"metadatas\"]/yt-formatted-string"));
                String element[] = elements.getText().split("• ");

                CompareMusicDto compareMusicDto = CompareMusicDto.builder().build();
                compareMusicDto.setMusicName("YoutubeMusic");
                compareMusicDto.setMusicPlandescription(element[0]); //요금제 설명
                compareMusicDto.setMusicPrice(element[1]); //가격
                compareMusicRepository.save(compareMusicDto.toEntity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            down();
        }
    }

    public void down() {
        driver.close();	//탭 닫기
        driver.quit();	//브라우저 닫기
    }
}
