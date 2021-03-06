package service;

import domain.Champion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.MockRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockServiceTest {

    @Mock
    private MockRepository mockRepository;

    @InjectMocks
    private MockService mockService;

    // ******************************************
    // 기본 mock test method 연습
    // ******************************************

    @Test
    public void 챔피언이름을가져오면_무조건_카이사를_리턴한다() {
        Champion champion = mock(Champion.class);
        //champion.getName()을 호출하면 "카이사"를 리턴한다.
        when(champion.getName()).thenReturn("카이사");
        assertThat(champion.getName(), is("카이사"));
        System.out.println("Champion : "+ champion.getName());
    }

    @Test
    public void  연예인이름을가지고오면_무조건_아이즈원을_리턴(){
        Champion singer = mock(Champion.class);
        when(singer.getName()).thenReturn("아이즈원");
        assertTrue(singer.getName() == "아이즈원");
        assertThat(singer.getName(), is("아이즈원"));
    }

    // 1. when, thenReturn을 사용하여 어떠한 챔피언 이름을 입력해도 베인을 리턴하도록 테스트하세요
    @Test
    public void mustreturnvayenWhenAnyChampionName(){
        Champion champion = mock(Champion.class);
        champion.setName("티모");
        when(champion.getName()).thenReturn("베인");
        assertThat(champion.getName(), is("베인"));
    }

    @Test
    public void mustReturnIzoneWhenAnyidolName(){
        Champion idol = mock(Champion.class);
        idol.setName("아이즈원");
        when(idol.getName()).thenReturn("아이즈원");
        assertThat(idol.getName(), is("아이즈원"));
        assertTrue(idol.getName() == "아이즈원");

        System.out.println(idol.getName());
    }
    // 2. 챔피언 이름으로 야스오를 저장하면, doThrow를 사용하여 Exception이 발생하도록 테스트 하세요.
    @Test(expected = IllegalArgumentException.class)
    public void madeExceptionWhenSetNameYasuo (){
        Champion champion = mock(Champion.class);
        doThrow(new IllegalArgumentException()).when(champion).setName(("야스오"));
        champion.setName("야스오");
    }

    @Test(expected = IllegalArgumentException.class)
    public void madeExceptionWhenSetNameIzone( ){
        Champion izone = mock(Champion.class);
        doThrow(new IllegalArgumentException()).when(izone).setName(("아이즈원"));
        izone.setName("아이즈원");
    }

    // 3. verify 를 사용하여 '미드' 포지션을 저장하는 프로세스가 진행되었는지 테스트 하세요.
    @Test
    public void SetMidPositionUseVerify (){
        Champion champion = mock(Champion.class);
        champion.setName("직스");
        champion.setPosition("미드");
        champion.setHasSkinCount(7);
        verify(champion, times(1)).setPosition("미드");
        verify(champion, times(1)).setName("직스");
        verify(champion, times(1)).setHasSkinCount(7);

    }

    @Test
    public void setLapperPositionUseVerify(){
        Champion izone = mock(Champion.class);
        izone.setName("예나");
        izone.setPosition("래퍼");
        verify(izone, times(1)).setPosition("래퍼");
    }



    // 4. champion 객체의 크기를 검증하는 로직이 1번 실행되었는지 테스트 하세요.
    @Test
    public void verifyObjectSize(){
        //ArrayList<Champion> championList = mock(ArrayList.class);
        List<Champion> monkChampions =mock(List.class);
        ArrayList<Champion> mockIzone = mock(ArrayList.class);

        Champion champion = mock(Champion.class);
        Champion izone = mock(Champion.class);
        champion.setName("애쉬");
        champion.setHasSkinCount(10);
        champion.setPosition("원딜");
        monkChampions.add(champion);

        izone.setName("채원");
        izone.setPosition("리드보컬");
        mockIzone.add(izone);

        System.out.println("Size :: "+monkChampions.size()); //값이 나오지 않는다.
        System.out.println("Size :: "+mockIzone.size()); //값이 나오지 않는다.
        verify(monkChampions, times(1)).size();
        verify(mockIzone, times(1)).size();

    }

    // 4-1. champion 객체에서 이름을 가져오는 로직이 2번 이상 실행되면 Pass 하는 로직을 작성하세요.
    @Test
    public void shouldTwoTimesINvocationForGetChampionName(){
        Champion champion = mock(Champion.class);
        Champion izone = mock(Champion.class);

        champion.setName("제드");
        champion.setPosition("미드");
        champion.setHasSkinCount(10);

        izone.setName("혜원");
        izone.getName();
        izone.getName();
        System.out.println(izone.getName());
        verify(izone, atLeast(2)).getName();

        System.out.println("Champion : "+champion.getName());
        System.out.println("Champion : "+champion.getName());

        verify(champion, atLeast(2)).getName();
    }
    // 4-2. champion 객체에서 이름을 가져오는 로직이 최소 3번 이하 실행되면 Pass 하는 로직을 작성하세요.
    @Test
    public void shouldlessThanThreeTimesInvocationForGetChampionName(){
        Champion champion = mock(Champion.class);
        champion.setName("오른");
        champion.setPosition("탑");
        champion.setHasSkinCount(1);

        System.out.println("Champion : "+champion.getName());
        System.out.println("Champion : "+champion.getName());
        System.out.println("Champion : "+champion.getName());
        System.out.println("Champion : "+champion.getName());

        verify(champion, atMost(3)).getName();
    }
    // 4-3. champion 객체에서 이름을 저장하는 로직이 실행되지 않았으면 Pass 하는 로직을 작성하세요.
    @Test
    public void ifSetChampionNameisNot (){
        Champion champion= mock(Champion.class);
        //champion.setName("오른");
        champion.setPosition("탑");
        champion.setHasSkinCount(1);

        verify(champion,never()).setName(any(String.class));
        verify(champion,never()).setName(anyString());
    }
    // 4-4. champion 객체에서 이름을 가져오는 로직이 200ms 시간 이내에 1번 실행되었는 지 검증하는 로직을 작성하세요.

    @Test
    public void OneTimeInvocationIN200msForChampionGetName() {
        Champion champion = mock(Champion.class);
        champion.setName("트페");
        champion.setPosition("미드");
        champion.setHasSkinCount(8);

        champion.getName();
        verify(champion, timeout(200).atLeastOnce()).getName();
    }

    // ******************************************
    // injectmock test 연습
    // ******************************************

    @Test
    public void 챔피언정보들을Mocking하고Service메소드호출테스트() {
        //when(mockService.findByName(anyString())).thenReturn(new Champion("루시안", "바텀", 5));
        //String championName = mockService.findByName("애쉬").getName();
        //assertThat(championName, is("루시안"));
        //verify(mockRepository, times(1)).findByName(anyString());

        when(mockService.findByName(anyString())).thenReturn(new Champion("루시안","바텀",5));
        String championName = mockService.findByName("애쉬").getName();
        assertThat(championName, is("루시안"));
        verify(mockRepository, times(1)).findByName(anyString());
    }

    // 1. 리산드라라는 챔피언 이름으로 검색하면 미드라는 포지션과 함께 가짜 객체를 리턴받고, 포지션이 탑이 맞는지를 테스트하세요
    @Test
    public void returnNotRealObjectAndCheckPositionIsTop (){
        when(mockService.findByName(anyString())).thenReturn((new Champion("리산드라","미드",5)));
        assertThat(mockService.findByName("리산드라").getPosition(), is("탑"));
        assertThat(mockService.findByName("리산드라").getHasSkinCount(), is(5));
        assertThat(mockService.findByName("리산드라").getName(), is("리산드라"));

        //Champion champion = mockService.findByName("리산드라");
        //assertThat(champion.getPosition(),is("탑"));

    }

    // 2. 2개 이상의 챔피언을 List로 만들어 전체 챔피언을 가져오는 메소드 호출시 그 갯수가 맞는지 확인하는 테스트 코드를 작성하세요.
    @Test
    public void ifCallMethodCheckMethodNum(){
        List<Champion> mockChampion = mock(List.class);
        Champion champion1 = mock(Champion.class);
        Champion champion2 = mock(Champion.class);

        champion1.setName("마스터이");
        champion1.setPosition("미드");
        champion1.setHasSkinCount(10);

        champion2.setName("티모");
        champion2.setPosition("서폿");
        champion2.setHasSkinCount(8);

        mockChampion.add(champion1);
        mockChampion.add(champion2);

        List<Champion> champions = new ArrayList<Champion>();
        champions.add(new Champion("루시안", "바텀",5));
        champions.add(new Champion("애쉬", "바텀",5));
        champions.add(new Champion("유미", "서폿",5));

        when(mockService.findAllChampions()).thenReturn(champions);
        assertThat(mockService.findAllChampions().size(),is(2));
        verify(mockRepository, times(1)).findAll();

    }
    // 3. 챔피언을 검색하면 가짜 챔피언 객체를 리턴하고, mockRepository의 해당 메소드가 1번 호출되었는지를 검증하고, 그 객체의 스킨 개수가
    //    맞는지 확인하는 테스트코드를 작성하세요.

    @Test
    public void returnNotrealObjectVerifyCallMethodOnceAndCheckSkinCount(){
        when(mockService.findByName(anyString())).thenReturn(new Champion("오공","탑",3));
        assertThat(mockService.findByName("르블랑").getHasSkinCount(), is(3));
        verify(mockRepository, times(1)).findByName(anyString());
    }
    // 4. 2개 이상의 가짜 챔피언 객체를 List로 만들어 리턴하고, 하나씩 해당 객체를 검색한 뒤 검색을 위해 호출한 횟수를 검증하세요.
    @Test
    public void returnNotRealObjectToListSearchAndVerifyNumOfCall (){
        List<Champion> champions = new ArrayList<Champion>();
        champions.add(new Champion("루사안","바텀",5));
        champions.add(new Champion("유미","서폿",3));
        champions.add(new Champion("애쉬","바텀",6));

        when(mockService.findAllChampions()).thenReturn(champions);
        //assertThat(mockService.findByName("루시안").getName(),is("루시안"));
        assertThat(mockService.findAllChampions().get(0).getName(),is("루시안"));
        assertThat(mockService.findAllChampions().size(),is(3));
        verify(mockRepository, times(2)).findAll();

    }

    //가장 많이 사용되는 테스트 중 하나로 BDD 방식에 기반한 테스트 방법 예제
    @Test
    public void 탐켄치를_호출하면_탐켄치정보를_리턴하고_1번이하로_호출되었는지_검증() {
        //given
        given(mockRepository.findByName("탐켄치")).willReturn(new Champion("탐켄치", "서폿", 4));
        //when
        Champion champion = mockService.findByName("탐켄치");
        //then
        verify(mockRepository, atLeast(1)).findByName(anyString());
        assertThat(champion.getName(), is("탐켄치"));
    }
}