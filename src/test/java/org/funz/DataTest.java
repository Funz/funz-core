package org.funz;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.funz.util.Data;
import org.funz.util.Format;
import org.junit.Before;
import org.junit.Test;
import static org.funz.util.Data.asObject;
import static org.funz.util.Data.asString;
import static org.funz.util.Format.ArrayMapToCSVString;
import static org.funz.util.Format.ArrayMapToJSONString;
import static org.funz.util.Format.ArrayMapToMDString;
import static org.funz.util.Format.ArrayMapToXMLString;

/**
 *
 * @author richet
 */
public class DataTest {

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main(DataTest.class.getName());
    }

    Map m;
    Map mm;
    Map mNULLarray;
    Map m0array;
    Map marray;
    Map mcomma;

    @Before
    public void setUp() throws Exception {
        m = new HashMap();
        m.put("a1", 132.0);
        m.put("b", "cdef");
        m.put("bb", "'cdef'");
        m.put("c", null);
        System.out.println("m= " + m);

        mNULLarray = new HashMap();
        mNULLarray.put("d", null);
        mNULLarray.put("e", null);
        //marray.put("f", new double[][]{{0.123, 0.456, 0.789}, {0.123, 0.456, 0.789}, {0.123, 0.456, 0.789}});
        System.out.println("mNULLarray= " + mNULLarray);

        m0array = new HashMap();
        m0array.put("e", new double[]{0.123, 0.456, 0.789});
        m0array.put("d", new String[]{null});
        System.out.println("m0array= " + m0array);

        marray = new HashMap();
        marray.put("d", new String[]{"a\naa", "b"});
        marray.put("e", new double[]{0.123, 0.456, 0.789});
        //marray.put("f", new double[][]{{0.123, 0.456, 0.789}, {0.123, 0.456, 0.789}, {0.123, 0.456, 0.789}});
        System.out.println("marray= " + marray);

        mm = new HashMap();
        mm.putAll(m);
        mm.put("d", new String[]{"a", "b", "c"});
        mm.put("e", new double[]{0.123, 0.456, 0.789});
        Map m2 = new HashMap();
        m2.put("i", 123.0);
        m2.put("j", "abc");
        mm.put("m2", m2);
        System.out.println("mm= " + mm);

        mcomma = new HashMap();
        mcomma.put("a1", 132.0);
        mcomma.put("b", "cdef");
        mcomma.put("bb", "'cdef'");
        mcomma.put("c", "'1,2,3'");
        System.out.println("mcomma= " + mcomma);
    }

    @Test
    public void testMap() {
        String mAsString = asString(m);
        System.out.println("asString(m)= " + mAsString);
        System.out.println("asObject(asString(m))= " + asObject(mAsString));

        assert asString(asObject(mAsString)).equals(mAsString) : "Inconsistent asString/Object loop: " + asString(asObject(mAsString) + " != " + m);
        //System.err.println(((Object[])((Map)asObject(mAsString)).get("d")).length);

        String mNULLarrayAsString = asString(mNULLarray);
        System.out.println("asString(mNULLarray)= " + mNULLarrayAsString);
        System.out.println("asObject(asString(mNULLarray))= " + asObject(mNULLarrayAsString));

        assert asString(asObject(mNULLarrayAsString)).equals(mNULLarrayAsString) : "Inconsistent asString/Object loop: " + asString(asObject(mNULLarrayAsString));
        //System.err.println(((Object[])((Map)asObject(mAsString)).get("d")).length);

        String m0arrayAsString = asString(m0array);
        System.out.println("asString(m0array)= " + m0arrayAsString);
        System.out.println("asObject(asString(m0array))= " + asObject(m0arrayAsString));

        assert asString(asObject(m0arrayAsString)).equals(m0arrayAsString) : "Inconsistent asString/Object loop: " + asString(asObject(m0arrayAsString));
        //System.err.println(((Object[])((Map)asObject(mAsString)).get("d")).length);


        String marrayAsString = asString(marray);
        System.out.println("asString(marray)= " + marrayAsString);
        System.out.println("asObject(asString(marray))= " + asObject(marrayAsString));

        assert asString(asObject(marrayAsString)).equals(marrayAsString) : "Inconsistent asString/Object loop: " + asString(asObject(marrayAsString));
        //System.err.println(((Object[])((Map)asObject(mAsString)).get("d")).length);

        String mmAsString = asString(mm);
        System.out.println("asString(mm)= " + mmAsString);
        System.out.println("asObject(asString(mm))= " + asObject(mmAsString));

        assert asString(asObject(mmAsString)).equals(mmAsString) : "Inconsistent asString/Object loop: " + asString(asObject(mmAsString));
        //System.err.println(((Object[])((Map)asObject(mAsString)).get("d")).length);


        String mcommaAsString = asString(mcomma);
        System.out.println("asString(mcomma)= " + mcommaAsString);
        System.out.println("asObject(asString(mcomma))= " + asObject(mcommaAsString));

        assert asString(asObject(mcommaAsString)).equals(mcommaAsString) : "Inconsistent asString/Object loop: " + asString(asObject(mcommaAsString));
        //System.err.println(((Object[])((Map)asObject(mAsString)).get("d")).length);

        //String modelx = "{&quot;x1&quot;:&quot;Unif(10,20)&quot;,&quot;x2&quot;:&quot;Norm(0,1)&quot;}";
        //final Map<Object, Object> asMapObject = Data.asMapObject(Format.fromHTML(modelx));
        String modelx = "{\"x1\":\"Unif(10,20)\",\"x2\":\"Norm(0,1)\"}";
        final Map<Object, Object> asMapObject = Data.asMapObject(modelx);
        Map<String, String> map_modelx = asMapObject.entrySet().stream().collect(
            Collectors.toMap(
                e -> e.getKey().toString().replace("\"", ""), 
                e -> e.getValue().toString()));
        System.out.println(modelx);
        System.out.println(asString(map_modelx,true,":"));
        
        assert asString(map_modelx,true,":").equals(modelx) : "Inconsistent asString/Object loop: " + asString(map_modelx,true,":");
    }

    //@Test
    public void testBasic() {
        assert asString(null) == null : "Failed to stringify null: " + asString(null)+ "("+ asString(null).getClass()+")";

        System.err.println("asString(new Integer[]{1,2,3}) " + (new int[]{1, 2, 3}));
        System.err.println("asString(new Integer[]{1,2,3}) " + asString(new Integer[]{1, 2, 3}));
        System.err.println("asString(new int[]{1,2,3}) " + asString(new int[]{1, 2, 3}));
        assert asString(new int[]{1, 2, 3}).equals("[1,2,3]") : "Failed to stringify int[]: " + asString(new int[]{1, 2, 3});

        String mAsString = asString(m);
        System.out.println("asString(m)= " + mAsString);
        System.out.println("asObject(asString(m))= " + asObject(mAsString));

        assert asString(asObject(mAsString)).equals(mAsString) : "Inconsistent asString/Object loop: " + asString(asObject(mAsString));
        //System.err.println(((Object[])((Map)asObject(mAsString)).get("d")).length);

        assert asObject("") == null : "! asObject(\"\")= " + asObject("");
        assert asObject("[]") instanceof Object[] : "! asObject(\"[]\") = " + asObject("[]");
    }

    //@Test
    public void testExport() {
        System.out.println("ArrayMapToString(null)= " + ArrayMapToMDString(null));
        System.out.println("ArrayMapToCSVString(null)= " + ArrayMapToCSVString(null));
        System.out.println("ArrayMapToXMLString(null)= " + ArrayMapToXMLString(null));
        System.out.println("ArrayMapToJSONString(null)= " + ArrayMapToJSONString(null));
        
        System.out.println("ArrayMapToString(mNULLarray)= " + ArrayMapToMDString(mNULLarray));
        System.out.println("ArrayMapToCSVString(mNULLarray)= " + ArrayMapToCSVString(mNULLarray));
        System.out.println("ArrayMapToXMLString(mNULLarray)= " + ArrayMapToXMLString(mNULLarray));
        System.out.println("ArrayMapToJSONString(mNULLarray)= " + ArrayMapToJSONString(mNULLarray));

        System.out.println("ArrayMapToString(m0array)= " + ArrayMapToMDString(m0array));
        System.out.println("ArrayMapToCSVString(m0array)= " + ArrayMapToCSVString(m0array));
        System.out.println("ArrayMapToXMLString(m0array)= " + ArrayMapToXMLString(m0array));
        System.out.println("ArrayMapToJSONString(m0array)= " + ArrayMapToJSONString(m0array));

        System.out.println("ArrayMapToString(marray)= " + ArrayMapToMDString(marray));
        System.out.println("ArrayMapToCSVString(marray)= " + ArrayMapToCSVString(marray));
        System.out.println("ArrayMapToXMLString(marray)= " + ArrayMapToXMLString(marray));
        System.out.println("ArrayMapToJSONString(marray)= " + ArrayMapToJSONString(marray));
    }

    //@Test
    public void testCast() {
        Object d1 = asObject("[11.1,11.2]");
        assert d1 instanceof double[] : "Failed to cast double[]:" + d1;

        Object d2 = asObject("[[10.0,11.0],[1.1,1.2],[1.5,1.6]]");
        assert d2 instanceof double[][] : "Failed to cast double[][]";

        Object d3 = asObject("[[[10.0,11.0],[1.1,1.2],[1.5]],[[110.0,111.0],[11.1,11.2],[11.5]]]");
        assert d3 instanceof double[][][] : "Failed to cast double[][][]";
    }

    //@Test
    public void testCastsUncast() {
        String[] test = {"[[0.007479637,0.0,-1.0,-1.0,-1.0],[0.01495927,0.0,-1.0,-1.0,-1.0],[0.02243891,0.0,-1.0,-1.0,-1.0],[0.04211135,0.0,-1.0,-1.0,-1.0],[0.07062997,0.0,-1.0,-1.0,-1.0],[0.1138578,0.0,-1.0,-1.0,-1.0],[0.1725668,0.0,-1.0,-1.0,-1.0],[0.2521548,0.0,-1.0,-1.0,-1.0],[0.3568887,0.0,-1.0,-1.0,-1.0],[0.4944325,0.0,-1.0,-1.0,-1.0],[0.6749782,0.0,-1.0,-1.0,-1.0],[1.007574,0.0,-1.0,-1.0,-1.0],[1.406248,0.0,-1.0,-1.0,-1.0],[1.914564,0.0,5.068959E-100,-1.0,5.677687E-100],[2.531476,0.0,1.105848E-94,1.328007E-95,1.238649E-94],[3.341715,0.0,1.659927E-88,1.993396E-89,1.859266E-88],[4.414811,0.0,1.127893E-82,1.35448E-83,1.263341E-82],[5.670199,0.0,4.819525E-79,5.787738E-80,5.398299E-79],[7.315341,0.0,2.230476E-74,2.678565E-75,2.498333E-74],[9.175708,0.0,1.161017E-71,1.394258E-72,1.300443E-71],[11.42802,0.0,9.543519E-69,1.146075E-69,1.068959E-68],[13.99293,0.0,1.877758E-66,2.254988E-67,2.103257E-66],[16.39094,0.0,3.328583E-65,3.997275E-66,3.728311E-65],[19.07619,0.0,8.504673E-64,1.021321E-64,9.525994E-64],[21.8779,0.0,1.508407E-62,1.811436E-63,1.68955E-62],[25.02253,0.0,2.9228E-61,3.509973E-62,3.273797E-61],[28.38712,0.0,4.197006E-60,5.040159E-61,4.701021E-60],[32.03944,0.0,4.961346E-59,5.95805E-60,5.557151E-59],[35.99929,0.0,4.764212E-58,5.721313E-59,5.336343E-58],[40.2023,0.0,3.51297E-57,4.218705E-58,3.934841E-57],[44.69006,0.0,2.082468E-56,2.500823E-57,2.33255E-56],[49.47568,0.0,9.995446E-56,1.200347E-56,1.119579E-55],[54.59364,0.0,3.922726E-55,4.710778E-56,4.393804E-55],[60.00006,0.0,1.241411E-54,1.490802E-55,1.390491E-54],[65.80709,0.0,3.236737E-54,3.886978E-55,3.625435E-54],[71.8828,0.0,6.792441E-54,8.157001E-55,7.608141E-54],[78.35282,0.0,1.168857E-53,1.403673E-54,1.309224E-53],[85.15081,0.0,1.651575E-53,1.983366E-54,1.849911E-53],[92.33116,0.0,1.965457E-53,2.360305E-54,2.201487E-53],[100.6562,0.0,2.066706E-53,2.481894E-54,2.314895E-53],[109.3387,0.0,2.043359E-53,2.453858E-54,2.288745E-53],[118.5334,0.0,2.029977E-53,2.437787E-54,2.273756E-53],[129.3921,0.0,2.037945E-53,2.447355E-54,2.28268E-53],[148.7928,0.0,2.036715E-53,2.445878E-54,2.281302E-53],[173.1589,0.0,2.035768E-53,2.444741E-54,2.280242E-53],[215.0508,0.0,2.037423E-53,2.446729E-54,2.282096E-53],[295.3073,0.0,2.035614E-53,2.444556E-54,2.28007E-53],[441.0265,0.0,2.03745E-53,2.446761E-54,2.282126E-53],[705.8067,0.0,2.035612E-53,2.444554E-54,2.280068E-53],[1183.146,0.0,2.037447E-53,2.446758E-54,2.282123E-53],[2041.731,0.0,2.035615E-53,2.444557E-54,2.28007E-53],[3587.838,0.0,2.037446E-53,2.446756E-54,2.282121E-53],[6369.581,0.0,2.035616E-53,2.444558E-54,2.280071E-53],[11372.44,0.0,2.037445E-53,2.446755E-54,2.28212E-53],[20375.86,0.0,2.035616E-53,2.444559E-54,2.280072E-53],[36566.92,0.0,2.037445E-53,2.446755E-54,2.28212E-53],[65700.48,0.0,2.035616E-53,2.444559E-54,2.280072E-53],[72000.0,0.0,2.037445E-53,2.446755E-54,2.28212E-53],[72000.01,0.0,2.037442E-53,2.446752E-54,2.282117E-53],[72000.01,0.0,2.03744E-53,2.446749E-54,2.282115E-53],[72000.02,0.0,2.037437E-53,2.446746E-54,2.282112E-53],[72001.75,0.0,2.036928E-53,2.446135E-54,2.281541E-53],[72003.97,0.0,2.036489E-53,2.445607E-54,2.28105E-53],[72006.33,0.0,2.036307E-53,2.445389E-54,2.280846E-53],[72010.77,0.0,2.036384E-53,2.445481E-54,2.280932E-53],[72017.3,0.0,2.036579E-53,2.445715E-54,2.28115E-53],[72024.25,0.0,2.036547E-53,2.445677E-54,2.281114E-53],[72034.96,0.0,2.036377E-53,2.445472E-54,2.280924E-53],[72044.73,0.0,2.035285E-53,2.444161E-54,2.279701E-53],[72053.08,0.0,2.031012E-53,2.43903E-54,2.274915E-53],[72060.74,0.0,2.018984E-53,2.424586E-54,2.261443E-53],[72068.36,0.0,1.989439E-53,2.389106E-54,2.22835E-53],[72075.47,0.0,1.934167E-53,2.322729E-54,2.16644E-53],[72081.96,0.0,1.851318E-53,2.223237E-54,2.073642E-53],[72087.96,0.0,1.74485E-53,2.095379E-54,1.954388E-53],[72093.53,0.0,1.624631E-53,1.951009E-54,1.819732E-53],[72098.0,0.0,1.519851E-53,1.825179E-54,1.702369E-53],[72100.0,0.0,1.472014E-53,1.767733E-54,1.648788E-53],[72100.01,0.0,1.471836E-53,1.767519E-54,1.648588E-53],[72100.01,0.0,1.471657E-53,1.767304E-54,1.648388E-53],[72100.02,0.0,1.471479E-53,1.76709E-54,1.648188E-53],[72100.25,0.0,1.466009E-53,1.760521E-54,1.642061E-53],[72100.46,0.0,1.461125E-53,1.754656E-54,1.63659E-53],[72100.72,0.0,1.454766E-53,1.74702E-54,1.629468E-53],[72100.97,0.0,1.448891E-53,1.739965E-54,1.622888E-53],[72101.28,0.0,1.441457E-53,1.731037E-54,1.614561E-53],[72101.65,0.0,1.432794E-53,1.720633E-54,1.604857E-53],[72102.12,0.0,1.421579E-53,1.707165E-54,1.592295E-53],[72102.8,0.0,1.405635E-53,1.688019E-54,1.574437E-53],[72103.48,0.0,1.389877E-53,1.669095E-54,1.556787E-53],[72104.35,0.0,1.369678E-53,1.644838E-54,1.534162E-53],[72105.16,0.0,1.351234E-53,1.622689E-54,1.513503E-53],[72106.13,0.0,1.329131E-53,1.596145E-54,1.488745E-53],[72107.02,0.0,1.309344E-53,1.572383E-54,1.466582E-53],[72108.05,0.0,1.286555E-53,1.545016E-54,1.441056E-53],[72109.14,0.0,1.262868E-53,1.51657E-54,1.414525E-53],[72110.34,0.0,1.237179E-53,1.48572E-54,1.385751E-53],[72111.59,0.0,1.210828E-53,1.454075E-54,1.356235E-53],[72112.91,0.0,1.183582E-53,1.421357E-54,1.325718E-53],[72114.26,0.0,1.156136E-53,1.388397E-54,1.294976E-53],[72115.64,0.0,1.128339E-53,1.355015E-54,1.26384E-53],[72117.04,0.0,1.100573E-53,1.321672E-54,1.23274E-53],[72118.46,0.0,1.07264E-53,1.288126E-54,1.201452E-53],[72119.89,0.0,1.044791E-53,1.254683E-54,1.170259E-53],[72121.51,0.0,1.013442E-53,1.217036E-54,1.135146E-53],[72123.09,0.0,9.829401E-54,1.180407E-54,1.100981E-53],[72124.67,0.0,9.525297E-54,1.143887E-54,1.066918E-53],[72126.4,0.0,9.194566E-54,1.10417E-54,1.029874E-53],[72128.07,0.0,8.873382E-54,1.065599E-54,9.938981E-54],[72129.9,0.0,8.521041E-54,1.023287E-54,9.544328E-54],[72131.64,0.0,8.185964E-54,9.830474E-55,9.169011E-54],[72133.53,0.0,7.820267E-54,9.391311E-55,8.759398E-54],[72135.48,0.0,7.438616E-54,8.932989E-55,8.331915E-54],[72137.55,0.0,7.033402E-54,8.44637E-55,7.878039E-54],[72139.5,0.0,6.651154E-54,7.987331E-55,7.449887E-54],[72141.54,0.0,6.248061E-54,7.503258E-55,6.998387E-54],[72143.63,0.0,5.836295E-54,7.008771E-55,6.537172E-54],[72145.79,0.0,5.409812E-54,6.496611E-55,6.059473E-54],[72147.99,0.0,4.976657E-54,5.976437E-55,5.574301E-54],[72150.23,0.0,4.537658E-54,5.449246E-55,5.082582E-54],[72152.5,0.0,4.097137E-54,4.920227E-55,4.58916E-54],[72154.79,0.0,3.657658E-54,4.39246E-55,4.096904E-54],[72157.1,0.0,3.223439E-54,3.871009E-55,3.61054E-54],[72159.61,0.0,2.765737E-54,3.321356E-55,3.097872E-54],[72162.08,0.0,2.335093E-54,2.804199E-55,2.615513E-54],[72164.56,0.0,1.930689E-54,2.318552E-55,2.162544E-54],[72167.01,0.0,1.565347E-54,1.879815E-55,1.753328E-54],[72169.63,0.0,1.21696E-54,1.46144E-55,1.363104E-54],[72172.19,0.0,9.249399E-55,1.110755E-55,1.036015E-54],[72174.92,0.0,6.675466E-55,8.016526E-56,7.477118E-55],[72177.55,0.0,4.713348E-55,5.660231E-56,5.279371E-55],[72180.35,0.0,3.135973E-55,3.765971E-56,3.51257E-55],[72183.04,0.0,2.045254E-55,2.456134E-56,2.290868E-55],[72185.88,0.0,1.251751E-55,1.50322E-56,1.402073E-55],[72188.59,0.0,7.539681E-56,9.054357E-57,8.445117E-56],[72191.24,0.0,4.441304E-56,5.333535E-57,4.974658E-56],[72193.54,0.0,2.731465E-56,3.2802E-57,3.059485E-56],[72195.66,0.0,1.713055E-56,2.057197E-57,1.918774E-56],[72197.62,0.0,1.094507E-56,1.314387E-57,1.225946E-56],[72199.5,0.0,7.030533E-57,8.442925E-58,7.874826E-57],[72201.3,0.0,4.544821E-57,5.457848E-58,5.090606E-57],[72203.04,0.0,2.949077E-57,3.541529E-58,3.30323E-57],[72204.72,0.0,1.922092E-57,2.308228E-58,2.152914E-57],[72206.35,0.0,1.256653E-57,1.509107E-58,1.407564E-57],[72207.95,0.0,8.239666E-58,9.894965E-59,9.229163E-58],[72209.5,0.0,5.414516E-58,6.502259E-59,6.064741E-58],[72211.03,0.0,3.565121E-58,4.281332E-59,3.993254E-58],[72212.52,0.0,2.3513E-58,2.823662E-59,2.633666E-58],[72213.99,0.0,1.553052E-58,1.86505E-59,1.739557E-58],[72215.44,0.0,1.027112E-58,1.233452E-59,1.150457E-58],[72216.86,0.0,6.800532E-59,8.166718E-60,7.617204E-59],[72218.27,0.0,4.507144E-59,5.412602E-60,5.048405E-59],[72219.67,0.0,2.978344E-59,3.576675E-60,3.336011E-59],[72221.05,0.0,1.971088E-59,2.367068E-60,2.207795E-59],[72222.41,0.0,1.305368E-59,1.567609E-60,1.462129E-59],[72223.76,0.0,8.656732E-60,1.039582E-60,9.696313E-60],[72225.1,0.0,5.743985E-60,6.897917E-61,6.433777E-60],[72226.42,0.0,3.814E-60,4.58021E-61,4.272021E-60],[72227.73,0.0,2.533745E-60,3.042759E-61,2.838021E-60],[72229.03,0.0,1.684204E-60,2.022551E-61,1.886459E-60],[72230.31,0.0,1.120041E-60,1.345051E-61,1.254546E-60],[72231.59,0.0,7.452083E-61,8.949161E-62,8.346999E-61],[72232.86,0.0,4.960213E-61,5.956689E-62,5.555882E-61],[72234.12,0.0,3.302882E-61,3.966411E-62,3.699523E-61],[72235.37,0.0,2.200091E-61,2.642075E-62,2.464298E-61],[72236.61,0.0,1.465999E-61,1.760509E-62,1.64205E-61],[72237.84,0.0,9.771494E-62,1.173453E-62,1.094495E-61],[72239.07,0.0,6.514994E-62,7.823817E-63,7.297376E-62],[72240.29,0.0,4.344943E-62,5.217816E-63,4.866724E-62],[72241.5,0.0,2.898435E-62,3.480713E-63,3.246506E-62],[72242.71,0.0,1.933951E-62,2.32247E-63,2.166198E-62],[72243.91,0.0,1.290694E-62,1.549986E-63,1.445692E-62],[72245.1,0.0,8.615709E-63,1.034655E-63,9.650364E-63],[72246.29,0.0,5.752325E-63,6.907933E-64,6.443119E-63],[72247.47,0.0,3.841276E-63,4.612964E-64,4.302572E-63],[72248.65,0.0,2.565562E-63,3.080967E-64,2.873658E-63],[72249.82,0.0,1.7138E-63,2.058092E-64,1.919609E-63],[72250.99,0.0,1.144997E-63,1.37502E-64,1.282499E-63],[72252.16,0.0,7.650887E-64,9.187904E-65,8.569677E-64],[72253.32,0.0,5.113035E-64,6.140212E-65,5.727056E-64],[72254.47,0.0,3.41745E-64,4.103994E-65,3.827849E-64],[72255.62,0.0,2.284436E-64,2.743365E-65,2.558772E-64],[72256.77,0.0,1.527237E-64,1.834049E-65,1.710642E-64],[72257.91,0.0,1.021132E-64,1.226271E-65,1.143759E-64],[72259.05,0.0,6.828152E-65,8.199887E-66,7.648141E-65],[72260.19,0.0,4.566336E-65,5.483685E-66,5.114704E-65],[72261.32,0.0,3.054033E-65,3.66757E-66,3.42079E-65],[72262.45,0.0,2.042768E-65,2.453147E-66,2.288082E-65],[72263.57,0.0,1.366475E-65,1.640991E-66,1.530574E-65],[72264.69,0.0,9.141551E-66,1.097803E-66,1.023935E-65],[72265.81,0.0,6.116066E-66,7.344746E-67,6.85054E-66],[72266.93,0.0,4.0922E-66,4.914298E-67,4.58363E-66],[72268.04,0.0,2.738247E-66,3.288344E-67,3.067081E-66],[72269.15,0.0,1.83239E-66,2.200506E-67,2.05244E-66],[72270.26,0.0,1.226285E-66,1.472638E-67,1.373549E-66],[72271.37,0.0,8.207143E-67,9.855909E-68,9.192734E-67],[72272.47,0.0,5.493112E-67,6.596645E-68,6.152777E-67],[72273.57,0.0,3.676797E-67,4.415444E-68,4.118342E-67],[72274.67,0.0,2.461187E-67,2.955625E-68,2.75675E-67],[72275.76,0.0,1.647564E-67,1.97855E-68,1.845419E-67],[72276.85,0.0,1.102965E-67,1.324544E-68,1.235419E-67],[72277.94,0.0,7.384175E-68,8.867611E-69,8.270936E-68],[72279.03,0.0,4.943816E-68,5.936999E-69,5.537516E-68],[72280.12,0.0,3.310106E-68,3.975086E-69,3.707614E-68],[72281.2,0.0,2.216358E-68,2.66161E-69,2.482519E-68],[72282.28,0.0,1.484074E-68,1.782215E-69,1.662295E-68],[72283.36,0.0,9.937753E-69,1.193419E-69,1.113117E-68],[72284.44,0.0,6.654835E-69,7.99175E-70,7.45401E-69],[72285.52,0.0,4.456583E-69,5.351884E-70,4.991772E-69],[72286.59,0.0,2.984571E-69,3.584153E-70,3.342986E-69]]",
            //"[]",
            //"1",
            "1.0", null, "abcd", "[abcd,cdef]",
            //"[0,1]",
            "[0.0,1.0]", "[[10.0,11.0],[1.1,1.2],[1.5,1.6]]", "[[[10.0,11.0],[1.1,1.2],[1.5]],[[110.0,111.0],[11.1,11.2],[11.5]]]"};
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " -> ");
            String o = asString(asObject(test[i]));
            System.out.println(o);

            assert (o == null && test[i] == null) || o.equals(test[i]) : test[i]+" != "+o;
        }
    }

    //@Test
    public void testObject2String() {

        String[] test = {"[[0.007479637,0.0,-1.0,-1.0,-1.0],[0.01495927,0.0,-1.0,-1.0,-1.0],[0.02243891,0.0,-1.0,-1.0,-1.0],[0.04211135,0.0,-1.0,-1.0,-1.0],[0.07062997,0.0,-1.0,-1.0,-1.0],[0.1138578,0.0,-1.0,-1.0,-1.0],[0.1725668,0.0,-1.0,-1.0,-1.0],[0.2521548,0.0,-1.0,-1.0,-1.0],[0.3568887,0.0,-1.0,-1.0,-1.0],[0.4944325,0.0,-1.0,-1.0,-1.0],[0.6749782,0.0,-1.0,-1.0,-1.0],[1.007574,0.0,-1.0,-1.0,-1.0],[1.406248,0.0,-1.0,-1.0,-1.0],[1.914564,0.0,5.068959E-100,-1.0,5.677687E-100],[2.531476,0.0,1.105848E-94,1.328007E-95,1.238649E-94],[3.341715,0.0,1.659927E-88,1.993396E-89,1.859266E-88],[4.414811,0.0,1.127893E-82,1.35448E-83,1.263341E-82],[5.670199,0.0,4.819525E-79,5.787738E-80,5.398299E-79],[7.315341,0.0,2.230476E-74,2.678565E-75,2.498333E-74],[9.175708,0.0,1.161017E-71,1.394258E-72,1.300443E-71],[11.42802,0.0,9.543519E-69,1.146075E-69,1.068959E-68],[13.99293,0.0,1.877758E-66,2.254988E-67,2.103257E-66],[16.39094,0.0,3.328583E-65,3.997275E-66,3.728311E-65],[19.07619,0.0,8.504673E-64,1.021321E-64,9.525994E-64],[21.8779,0.0,1.508407E-62,1.811436E-63,1.68955E-62],[25.02253,0.0,2.9228E-61,3.509973E-62,3.273797E-61],[28.38712,0.0,4.197006E-60,5.040159E-61,4.701021E-60],[32.03944,0.0,4.961346E-59,5.95805E-60,5.557151E-59],[35.99929,0.0,4.764212E-58,5.721313E-59,5.336343E-58],[40.2023,0.0,3.51297E-57,4.218705E-58,3.934841E-57],[44.69006,0.0,2.082468E-56,2.500823E-57,2.33255E-56],[49.47568,0.0,9.995446E-56,1.200347E-56,1.119579E-55],[54.59364,0.0,3.922726E-55,4.710778E-56,4.393804E-55],[60.00006,0.0,1.241411E-54,1.490802E-55,1.390491E-54],[65.80709,0.0,3.236737E-54,3.886978E-55,3.625435E-54],[71.8828,0.0,6.792441E-54,8.157001E-55,7.608141E-54],[78.35282,0.0,1.168857E-53,1.403673E-54,1.309224E-53],[85.15081,0.0,1.651575E-53,1.983366E-54,1.849911E-53],[92.33116,0.0,1.965457E-53,2.360305E-54,2.201487E-53],[100.6562,0.0,2.066706E-53,2.481894E-54,2.314895E-53],[109.3387,0.0,2.043359E-53,2.453858E-54,2.288745E-53],[118.5334,0.0,2.029977E-53,2.437787E-54,2.273756E-53],[129.3921,0.0,2.037945E-53,2.447355E-54,2.28268E-53],[148.7928,0.0,2.036715E-53,2.445878E-54,2.281302E-53],[173.1589,0.0,2.035768E-53,2.444741E-54,2.280242E-53],[215.0508,0.0,2.037423E-53,2.446729E-54,2.282096E-53],[295.3073,0.0,2.035614E-53,2.444556E-54,2.28007E-53],[441.0265,0.0,2.03745E-53,2.446761E-54,2.282126E-53],[705.8067,0.0,2.035612E-53,2.444554E-54,2.280068E-53],[1183.146,0.0,2.037447E-53,2.446758E-54,2.282123E-53],[2041.731,0.0,2.035615E-53,2.444557E-54,2.28007E-53],[3587.838,0.0,2.037446E-53,2.446756E-54,2.282121E-53],[6369.581,0.0,2.035616E-53,2.444558E-54,2.280071E-53],[11372.44,0.0,2.037445E-53,2.446755E-54,2.28212E-53],[20375.86,0.0,2.035616E-53,2.444559E-54,2.280072E-53],[36566.92,0.0,2.037445E-53,2.446755E-54,2.28212E-53],[65700.48,0.0,2.035616E-53,2.444559E-54,2.280072E-53],[72000.0,0.0,2.037445E-53,2.446755E-54,2.28212E-53],[72000.01,0.0,2.037442E-53,2.446752E-54,2.282117E-53],[72000.01,0.0,2.03744E-53,2.446749E-54,2.282115E-53],[72000.02,0.0,2.037437E-53,2.446746E-54,2.282112E-53],[72001.75,0.0,2.036928E-53,2.446135E-54,2.281541E-53],[72003.97,0.0,2.036489E-53,2.445607E-54,2.28105E-53],[72006.33,0.0,2.036307E-53,2.445389E-54,2.280846E-53],[72010.77,0.0,2.036384E-53,2.445481E-54,2.280932E-53],[72017.3,0.0,2.036579E-53,2.445715E-54,2.28115E-53],[72024.25,0.0,2.036547E-53,2.445677E-54,2.281114E-53],[72034.96,0.0,2.036377E-53,2.445472E-54,2.280924E-53],[72044.73,0.0,2.035285E-53,2.444161E-54,2.279701E-53],[72053.08,0.0,2.031012E-53,2.43903E-54,2.274915E-53],[72060.74,0.0,2.018984E-53,2.424586E-54,2.261443E-53],[72068.36,0.0,1.989439E-53,2.389106E-54,2.22835E-53],[72075.47,0.0,1.934167E-53,2.322729E-54,2.16644E-53],[72081.96,0.0,1.851318E-53,2.223237E-54,2.073642E-53],[72087.96,0.0,1.74485E-53,2.095379E-54,1.954388E-53],[72093.53,0.0,1.624631E-53,1.951009E-54,1.819732E-53],[72098.0,0.0,1.519851E-53,1.825179E-54,1.702369E-53],[72100.0,0.0,1.472014E-53,1.767733E-54,1.648788E-53],[72100.01,0.0,1.471836E-53,1.767519E-54,1.648588E-53],[72100.01,0.0,1.471657E-53,1.767304E-54,1.648388E-53],[72100.02,0.0,1.471479E-53,1.76709E-54,1.648188E-53],[72100.25,0.0,1.466009E-53,1.760521E-54,1.642061E-53],[72100.46,0.0,1.461125E-53,1.754656E-54,1.63659E-53],[72100.72,0.0,1.454766E-53,1.74702E-54,1.629468E-53],[72100.97,0.0,1.448891E-53,1.739965E-54,1.622888E-53],[72101.28,0.0,1.441457E-53,1.731037E-54,1.614561E-53],[72101.65,0.0,1.432794E-53,1.720633E-54,1.604857E-53],[72102.12,0.0,1.421579E-53,1.707165E-54,1.592295E-53],[72102.8,0.0,1.405635E-53,1.688019E-54,1.574437E-53],[72103.48,0.0,1.389877E-53,1.669095E-54,1.556787E-53],[72104.35,0.0,1.369678E-53,1.644838E-54,1.534162E-53],[72105.16,0.0,1.351234E-53,1.622689E-54,1.513503E-53],[72106.13,0.0,1.329131E-53,1.596145E-54,1.488745E-53],[72107.02,0.0,1.309344E-53,1.572383E-54,1.466582E-53],[72108.05,0.0,1.286555E-53,1.545016E-54,1.441056E-53],[72109.14,0.0,1.262868E-53,1.51657E-54,1.414525E-53],[72110.34,0.0,1.237179E-53,1.48572E-54,1.385751E-53],[72111.59,0.0,1.210828E-53,1.454075E-54,1.356235E-53],[72112.91,0.0,1.183582E-53,1.421357E-54,1.325718E-53],[72114.26,0.0,1.156136E-53,1.388397E-54,1.294976E-53],[72115.64,0.0,1.128339E-53,1.355015E-54,1.26384E-53],[72117.04,0.0,1.100573E-53,1.321672E-54,1.23274E-53],[72118.46,0.0,1.07264E-53,1.288126E-54,1.201452E-53],[72119.89,0.0,1.044791E-53,1.254683E-54,1.170259E-53],[72121.51,0.0,1.013442E-53,1.217036E-54,1.135146E-53],[72123.09,0.0,9.829401E-54,1.180407E-54,1.100981E-53],[72124.67,0.0,9.525297E-54,1.143887E-54,1.066918E-53],[72126.4,0.0,9.194566E-54,1.10417E-54,1.029874E-53],[72128.07,0.0,8.873382E-54,1.065599E-54,9.938981E-54],[72129.9,0.0,8.521041E-54,1.023287E-54,9.544328E-54],[72131.64,0.0,8.185964E-54,9.830474E-55,9.169011E-54],[72133.53,0.0,7.820267E-54,9.391311E-55,8.759398E-54],[72135.48,0.0,7.438616E-54,8.932989E-55,8.331915E-54],[72137.55,0.0,7.033402E-54,8.44637E-55,7.878039E-54],[72139.5,0.0,6.651154E-54,7.987331E-55,7.449887E-54],[72141.54,0.0,6.248061E-54,7.503258E-55,6.998387E-54],[72143.63,0.0,5.836295E-54,7.008771E-55,6.537172E-54],[72145.79,0.0,5.409812E-54,6.496611E-55,6.059473E-54],[72147.99,0.0,4.976657E-54,5.976437E-55,5.574301E-54],[72150.23,0.0,4.537658E-54,5.449246E-55,5.082582E-54],[72152.5,0.0,4.097137E-54,4.920227E-55,4.58916E-54],[72154.79,0.0,3.657658E-54,4.39246E-55,4.096904E-54],[72157.1,0.0,3.223439E-54,3.871009E-55,3.61054E-54],[72159.61,0.0,2.765737E-54,3.321356E-55,3.097872E-54],[72162.08,0.0,2.335093E-54,2.804199E-55,2.615513E-54],[72164.56,0.0,1.930689E-54,2.318552E-55,2.162544E-54],[72167.01,0.0,1.565347E-54,1.879815E-55,1.753328E-54],[72169.63,0.0,1.21696E-54,1.46144E-55,1.363104E-54],[72172.19,0.0,9.249399E-55,1.110755E-55,1.036015E-54],[72174.92,0.0,6.675466E-55,8.016526E-56,7.477118E-55],[72177.55,0.0,4.713348E-55,5.660231E-56,5.279371E-55],[72180.35,0.0,3.135973E-55,3.765971E-56,3.51257E-55],[72183.04,0.0,2.045254E-55,2.456134E-56,2.290868E-55],[72185.88,0.0,1.251751E-55,1.50322E-56,1.402073E-55],[72188.59,0.0,7.539681E-56,9.054357E-57,8.445117E-56],[72191.24,0.0,4.441304E-56,5.333535E-57,4.974658E-56],[72193.54,0.0,2.731465E-56,3.2802E-57,3.059485E-56],[72195.66,0.0,1.713055E-56,2.057197E-57,1.918774E-56],[72197.62,0.0,1.094507E-56,1.314387E-57,1.225946E-56],[72199.5,0.0,7.030533E-57,8.442925E-58,7.874826E-57],[72201.3,0.0,4.544821E-57,5.457848E-58,5.090606E-57],[72203.04,0.0,2.949077E-57,3.541529E-58,3.30323E-57],[72204.72,0.0,1.922092E-57,2.308228E-58,2.152914E-57],[72206.35,0.0,1.256653E-57,1.509107E-58,1.407564E-57],[72207.95,0.0,8.239666E-58,9.894965E-59,9.229163E-58],[72209.5,0.0,5.414516E-58,6.502259E-59,6.064741E-58],[72211.03,0.0,3.565121E-58,4.281332E-59,3.993254E-58],[72212.52,0.0,2.3513E-58,2.823662E-59,2.633666E-58],[72213.99,0.0,1.553052E-58,1.86505E-59,1.739557E-58],[72215.44,0.0,1.027112E-58,1.233452E-59,1.150457E-58],[72216.86,0.0,6.800532E-59,8.166718E-60,7.617204E-59],[72218.27,0.0,4.507144E-59,5.412602E-60,5.048405E-59],[72219.67,0.0,2.978344E-59,3.576675E-60,3.336011E-59],[72221.05,0.0,1.971088E-59,2.367068E-60,2.207795E-59],[72222.41,0.0,1.305368E-59,1.567609E-60,1.462129E-59],[72223.76,0.0,8.656732E-60,1.039582E-60,9.696313E-60],[72225.1,0.0,5.743985E-60,6.897917E-61,6.433777E-60],[72226.42,0.0,3.814E-60,4.58021E-61,4.272021E-60],[72227.73,0.0,2.533745E-60,3.042759E-61,2.838021E-60],[72229.03,0.0,1.684204E-60,2.022551E-61,1.886459E-60],[72230.31,0.0,1.120041E-60,1.345051E-61,1.254546E-60],[72231.59,0.0,7.452083E-61,8.949161E-62,8.346999E-61],[72232.86,0.0,4.960213E-61,5.956689E-62,5.555882E-61],[72234.12,0.0,3.302882E-61,3.966411E-62,3.699523E-61],[72235.37,0.0,2.200091E-61,2.642075E-62,2.464298E-61],[72236.61,0.0,1.465999E-61,1.760509E-62,1.64205E-61],[72237.84,0.0,9.771494E-62,1.173453E-62,1.094495E-61],[72239.07,0.0,6.514994E-62,7.823817E-63,7.297376E-62],[72240.29,0.0,4.344943E-62,5.217816E-63,4.866724E-62],[72241.5,0.0,2.898435E-62,3.480713E-63,3.246506E-62],[72242.71,0.0,1.933951E-62,2.32247E-63,2.166198E-62],[72243.91,0.0,1.290694E-62,1.549986E-63,1.445692E-62],[72245.1,0.0,8.615709E-63,1.034655E-63,9.650364E-63],[72246.29,0.0,5.752325E-63,6.907933E-64,6.443119E-63],[72247.47,0.0,3.841276E-63,4.612964E-64,4.302572E-63],[72248.65,0.0,2.565562E-63,3.080967E-64,2.873658E-63],[72249.82,0.0,1.7138E-63,2.058092E-64,1.919609E-63],[72250.99,0.0,1.144997E-63,1.37502E-64,1.282499E-63],[72252.16,0.0,7.650887E-64,9.187904E-65,8.569677E-64],[72253.32,0.0,5.113035E-64,6.140212E-65,5.727056E-64],[72254.47,0.0,3.41745E-64,4.103994E-65,3.827849E-64],[72255.62,0.0,2.284436E-64,2.743365E-65,2.558772E-64],[72256.77,0.0,1.527237E-64,1.834049E-65,1.710642E-64],[72257.91,0.0,1.021132E-64,1.226271E-65,1.143759E-64],[72259.05,0.0,6.828152E-65,8.199887E-66,7.648141E-65],[72260.19,0.0,4.566336E-65,5.483685E-66,5.114704E-65],[72261.32,0.0,3.054033E-65,3.66757E-66,3.42079E-65],[72262.45,0.0,2.042768E-65,2.453147E-66,2.288082E-65],[72263.57,0.0,1.366475E-65,1.640991E-66,1.530574E-65],[72264.69,0.0,9.141551E-66,1.097803E-66,1.023935E-65],[72265.81,0.0,6.116066E-66,7.344746E-67,6.85054E-66],[72266.93,0.0,4.0922E-66,4.914298E-67,4.58363E-66],[72268.04,0.0,2.738247E-66,3.288344E-67,3.067081E-66],[72269.15,0.0,1.83239E-66,2.200506E-67,2.05244E-66],[72270.26,0.0,1.226285E-66,1.472638E-67,1.373549E-66],[72271.37,0.0,8.207143E-67,9.855909E-68,9.192734E-67],[72272.47,0.0,5.493112E-67,6.596645E-68,6.152777E-67],[72273.57,0.0,3.676797E-67,4.415444E-68,4.118342E-67],[72274.67,0.0,2.461187E-67,2.955625E-68,2.75675E-67],[72275.76,0.0,1.647564E-67,1.97855E-68,1.845419E-67],[72276.85,0.0,1.102965E-67,1.324544E-68,1.235419E-67],[72277.94,0.0,7.384175E-68,8.867611E-69,8.270936E-68],[72279.03,0.0,4.943816E-68,5.936999E-69,5.537516E-68],[72280.12,0.0,3.310106E-68,3.975086E-69,3.707614E-68],[72281.2,0.0,2.216358E-68,2.66161E-69,2.482519E-68],[72282.28,0.0,1.484074E-68,1.782215E-69,1.662295E-68],[72283.36,0.0,9.937753E-69,1.193419E-69,1.113117E-68],[72284.44,0.0,6.654835E-69,7.99175E-70,7.45401E-69],[72285.52,0.0,4.456583E-69,5.351884E-70,4.991772E-69],[72286.59,0.0,2.984571E-69,3.584153E-70,3.342986E-69]]",
            //"[]",
            //"1",
            "1.0",
            "",
            "abcd",
            "'abcd'",
            "abcd\\nefgh",
            "[abcd,cdef]",
            //"[0,1]",
            "[0.0,1.0]",
            "[[10.0,11.0],[1.1,1.2],[1.5,1.6]]",
            "[[[10.0,11.0],[1.1,1.2],[1.5]],[[110.0,111.0],[11.1,11.2],[11.5]]]"};

        for (int i = 0; i < test.length; i++) {
            System.err.print(test[i] + " -> ");
            String o = asString(asObject(test[i]));
            System.err.println(o);
            if (o != null) {
                assert o.equals(test[i]) : test[i]+" != "+o;
            }
        }

        /*Object[] test = {null, new double[0], new Integer(1), new Double(1.0), "abcd", new String[]{"abcd", "cdef"}, new int[]{0, 1}, new double[]{0.0, 1.0}, new double[][]{{0.0, 1.0}, {1.1, 1.2}, {1.5}}};
         for (int i = 0; i < test.length; i++) {
         System.out.println(test[i] + " -> " + ObjecttoString(test[i]));
         //Object o = StringtoObject(ObjecttoString(test[i]));
         //if (o.getClass().isArray()) assert Arrays.deepEquals(o,test[i]) : test[i]; else
         //assert (o == null && test[i] == null) || o.equals(test[i]) : test[i];
         }*/
        //System.out.println(getLastFullLine(new File("/tmp/test/38505/spool/out.txt"))); //$NON-NLS-1$
    }

    //@Test
    public void testDeepCastsUncast() {
        Object[] test = {null, new double[0], /*new Integer(1), */ new Double(1.0), "abcd", new String[]{"abcd", "cdef"}, /*new int[]{0, 1},*/ new double[]{0.0, 1.0}, new double[][]{{0.0, 1.0}, {1.1, 1.2}, {1.5}}};
        for (int i = 0; i < test.length; i++) {
            System.err.println(test[i] + " -> " + asString(test[i]));
            Object o = asObject(asString(test[i]));
            if (o != null && o.getClass().isArray()) {
                if (org.apache.commons.lang.ArrayUtils.getLength(o) > 0) {
                    assert deepEquals(o, test[i]) : o + " != " + test[i];
                }
            } else {
                assert (o == null && test[i] == null) || (o != null && o.equals(test[i])) : o + " != " + test[i];
            }
        }
    }

    //@Test
    public void testString2Object() {

        Object[] test = {0.123, "abcd", "'abcd'", null, new double[]{123, 124, 0.123}, new String[]{"abcd", "cdef"}};

        for (int i = 0; i < test.length; i++) {
            System.err.println(test[i] + " -> ");
            Object o = asObject(asString(test[i]));
            System.err.println(o);
            System.err.println("\n");
            if (o != null) {

                assert o.equals(test[i]) || deepEquals(o, test[i]) : test[i]+" != "+o;
            }
        }
    }


    /*Object[] test = {null, new double[0], new Integer(1), new Double(1.0), "abcd", new String[]{"abcd", "cdef"}, new int[]{0, 1}, new double[]{0.0, 1.0}, new double[][]{{0.0, 1.0}, {1.1, 1.2}, {1.5}}};
     for (int i = 0; i < test.length; i++) {
     System.out.println(test[i] + " -> " + ObjecttoString(test[i]));
     //Object o = StringtoObject(ObjecttoString(test[i]));
     //if (o.getClass().isArray()) assert Arrays.deepEquals(o,test[i]) : test[i]; else
     //assert (o == null && test[i] == null) || o.equals(test[i]) : test[i];
     }*/
    //System.out.println(getLastFullLine(new File("/tmp/test/38505/spool/out.txt"))); //$NON-NLS-1$
    public static String[] asStringArray(Object[] array) {
        String[] sarray = new String[array.length];
        for (int i = 0; i < sarray.length; i++) {
            sarray[i] = asString(array[i]);
        }
        return sarray;
    }

    boolean deepEquals(Object o, Object oo) {
        if (o instanceof String[]) {
            String[] o_str = (String[]) o;
            String[] oo_str = (String[]) oo;

            boolean eq = o_str.length == oo_str.length;
            int i = 0;
            while (eq && i < o_str.length) {
                eq = eq && o_str[i].equals(oo_str[i]);
                i++;
            }
            return eq;
        } else if (o instanceof double[]) {
            return Arrays.equals((double[]) oo, (double[]) o);
        } else if (o instanceof Double[]) {
            return Arrays.equals((Double[]) oo, (Double[]) o);
        } else if (o instanceof Object[]) {
            return deepEquals(asStringArray((Object[]) o), asStringArray((Object[]) oo));
        } else {
            return o.equals(oo);
        }

    }
}
