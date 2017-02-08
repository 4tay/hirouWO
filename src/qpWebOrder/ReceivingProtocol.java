package qpWebOrder;

import java.util.Date;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.json.*;

public class ReceivingProtocol extends ReceiveMain
{
    Void run(JSONObject input)
    {
        try
        {
            String custName = input.getString("name");
            String tdate = input.getString("transactionDate");
            String pickupDate = input.getString("pickupDate");
            Integer subTotal = input.getInt("subTotal");
            Integer tax = input.getInt("tax");
            Integer total = input.getInt("total");
            String paymentMethod = input.getString("paymentMethod");
            String phoneNumber = input.getString("phoneNumber");
            String email = input.getString("email");
            String cardNum = input.getString("cardNumb");
			JSONArray items = input.getJSONArray("items");
            String itemNumber = "";
            String itemName = "";
            Integer itemPrice = 0;
            Integer itemQty = 0;
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd"+"."+"HHmmss"+"_"+"SSS");
            Date dateTime = new Date();
            String fileName=dateFormat.format(dateTime);
            File file = new File("C:\\Program Files (x86)\\Sequoia Retail Systems\\Quadpoint\\POS\\dat\\transactions\\" + fileName);

            System.out.println("filename:" + fileName);

            if (file.createNewFile())
            {
                System.out.println("Created file " + file.toString());
            }
            else
            {
                System.out.println("File already exists.");
            }

            FileWriter fWriter = new FileWriter(file,true);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            PrintWriter write = new PrintWriter(bWriter);

            write.println("[HEADER]");
            write.println("ORDER_NUMBER=10572217");
            write.println("TRANS_NUMBER=10572217");
            write.println("TRANS_TYPE=SALE");
            write.println("TRANS_DATE=" + tdate);
            write.println("TRANS_TIME=141202");
            write.println("PICKUP_DATE=" + pickupDate);
            write.println("PICKUP_TIME=142000");
            write.println("ITEMS_COUNT=1");
            write.println("TEND_SOURCE=");
            write.println("SUBTOTAL=" + subTotal);
            write.println("TAXABLE_AMOUNT=245");
            write.println("REAL_TAX=13");
            write.println("TAX=" + tax);
            write.println("TOTAL=" + total);
            write.println("CARD=");
            write.println("TENDER_DESCR=");
            write.println("KEY_NUM=");
            write.println("CASHIER_ID=Web Cashier");
            write.println("TRANSACTION_STRING_ID=");
            write.println("TAKE_OUT=NOT SET");
            write.println("ORIGIN_ID=0");
            write.println("DISCOUNT_AMOUNT=0");
            write.println("SOURCE=WEB");
            write.println("NUMBER_OF_SEATS=1");
            write.println("CHANGE_DUE=0");
            write.println("ORDER_STATUS=HOT");
            write.println("LOCATION_CODE=ryans pub-dinner");
            write.println("CUSTOMER_NAME=" + custName);
            write.println("EMAIL=" + email);
            write.println("PHONE=" + phoneNumber);
            write.println("CUSTOMER_ID=");
            write.println("DELIVERY_STREET_ADDRESS1=4909 Western Blvd");
            write.println("DELIVERY_STREET_ADDRESS2=");
            write.println("DELIVERY_CITY=Raleigh");
            write.println("DELIVERY_ZIPCODE=27606");
            write.println("DELIVERY_STATE=NC");
            write.println("DELIVERY_COUNTRY=US");
            write.println("DELIVERY_METHOD=PICKUP");
            write.println("");
            write.println("[TAX_BREAKDOWN]");
            write.println("NC State Tax=13");
            write.println("");
            write.println("[ITEMS]");

        for (int i = 0; i < items.length(); i++)
            {
            JSONObject item = items.getJSONObject(i);
            JSONObject anItem = item.getJSONObject("item");

            itemNumber = anItem.getString("itemNumber");
            itemName = anItem.getString("itemName");
            itemPrice = anItem.getInt("itemPrice");
            itemQty = anItem.getInt("itemQty");

            write.println("ITEMS" +i + "=" + itemNumber + "," + itemName + "," + itemPrice + ",subcat,cat," + itemQty + ",NO,NO,0,0,0,NO,NO,0,0,-,0, , , , ,-1,0");
            }

            write.println("MOD_ITEMS0=1005|245|Chargrilled Club Sandwich Option|0|0|1||||||||31303035~1032|0|CFA Side Options|0|0|1||||||||31303332~1072|0|CFA Drink Options|0|0|1||||||||31303732");
            write.println("COMMENTS_ITEMS0=");
            write.println("TAX_ITEMS0=NC State Tax|5500|13~");
            write.println("");
            write.println("[TENDERS]");
            write.println("TENDER1=CREDIT,Credit," + cardNum + ",258,258,accepted,YES,1362296,0,0,0,0,John Konderla,,,Credit,NO");
            write.println("");
            write.println("[TENDER_TAXES]");
            write.println("TENDER1_TAX=NC State Tax|13");

            write.println(paymentMethod);

            write.close();

        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return null;
    }
}