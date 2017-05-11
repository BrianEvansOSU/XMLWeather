import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Acclimates me with XML methods.
 *
 * @Brian Evans
 *
 */
public final class XMLTreeExploration {

    private XMLTreeExploration() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        XMLTree xml = new XMLTree1("https://query.yahooapis.com/v1/public/yql?q"
                + "=select+%2A+from+"
                + "weather.forecast+where+woeid%3D12776203" + "&format=xml");
        xml.display();
        String label = xml.label();
        if (xml.isTag()) {
            out.println("The XMLTree is a tag " + xml.label());
        } else {
            out.println("The XMLTree is a text");
        }
        out.println(label);
        XMLTree results = xml.child(0);
        XMLTree channel = results.child(0);
        out.println(channel.numberOfChildren());
        XMLTree title = channel.child(1);
        XMLTree titleText = title.child(0);
        out.println(titleText.toString());
        out.println(xml.child(0).child(0).child(1).child(0).toString());
        XMLTree astronomy = channel.child(10);
        if (astronomy.hasAttribute("midday")) {
            System.out.println("Has midday");
        } else {
            System.out.println("Does not have midday");
        }
        if (astronomy.hasAttribute("sunrise")) {
            System.out.println(
                    "Has sunrise at: " + astronomy.attributeValue("sunrise"));
        } else {
            System.out.println("Does not have sunrise");
        }
        if (astronomy.hasAttribute("sunset")) {
            System.out.println(
                    "Has sunset at: " + astronomy.attributeValue("sunset"));
        }
        in.close();
        out.close();
    }

}
