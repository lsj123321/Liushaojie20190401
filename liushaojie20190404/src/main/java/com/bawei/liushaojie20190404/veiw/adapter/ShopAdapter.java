package com.bawei.liushaojie20190404.veiw.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.liushaojie20190404.R;
import com.bawei.liushaojie20190404.model.bean.ShopBean;
import com.bumptech.glide.Glide;

public class ShopAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ShopBean arr;
    private CheckBox mcCheckBox;
    private TextView mTextView;

    private float priceAll=0;

    public void setMcCheckBox(CheckBox mcCheckBox) {
        this.mcCheckBox = mcCheckBox;
        mcCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setmTextView(TextView mTextView) {
        this.mTextView = mTextView;
    }

    public ShopAdapter(Context mcContext) {
        this.mContext = mcContext;
    }

    public void setArr(ShopBean arr) {
        this.arr = arr;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        if(arr==null){
            return 0;
        }
        return arr.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(arr==null){
            return 0;
        }
        return arr.getData().get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.group_shop_item,null);
            vh.checkBox=convertView.findViewById(R.id.checkbox_group);
            vh.name=convertView.findViewById(R.id.name_group);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        vh.checkBox.setChecked(arr.getData().get(groupPosition).isCheck_group());
        vh.name.setText(arr.getData().get(groupPosition).getSellerName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder2 vh;
        if (convertView==null){
            vh=new ViewHolder2();
            convertView=View.inflate(mContext, R.layout.child_shop_item,null);
            vh.checkBox=convertView.findViewById(R.id.checkbox_Child);
            vh.imageView=convertView.findViewById(R.id.img_Child);
            vh.name=convertView.findViewById(R.id.name_Child);
            vh.price=convertView.findViewById(R.id.price_Child);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder2) convertView.getTag();
        }
        vh.price.setText("￥："+arr.getData().get(groupPosition).getList().get(childPosition).getPrice());
        vh.name.setText(arr.getData().get(groupPosition).getList().get(childPosition).getTitle());
        vh.checkBox.setChecked(arr.getData().get(groupPosition).getList().get(childPosition).isCheck_Child());
        Glide.with(mContext).load(arr.getData().get(groupPosition).getList().get(childPosition).getImages()).into(vh.imageView);

        vh.checkBox.setTag(groupPosition+"#"+childPosition);
        vh.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox= (CheckBox) v;
                String tag= (String) checkBox.getTag();
                int gpostion=Integer.parseInt(tag.split("#")[0]);
                int cpostion=Integer.parseInt(tag.split("#")[1]);
                ShopBean.DataBean dataBean = arr.getData().get(gpostion);
                ShopBean.DataBean.ListBean listBean = arr.getData().get(gpostion).getList().get(cpostion);
                listBean.setCheck_Child(checkBox.isChecked());
                double price = listBean.getPrice();
                if (checkBox.isChecked()){
                    priceAll+=price;
                }else {
                    priceAll-=price;
                }
                mTextView.setText(priceAll+"");

                dataBean.setCheck_group(checkBox.isChecked());
                mcCheckBox.setChecked(isSelectAllGroup());
                notifyDataSetChanged();
            }

           
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    class ViewHolder{
        CheckBox checkBox;
        TextView name;
    }
    class ViewHolder2{
        CheckBox checkBox;
        ImageView imageView;
        TextView name,price;
    }
    //选中全部组
    private boolean isSelectAllGroup() {
        for (int i = 0; i < arr.getData().size(); i++) {
            if (!(arr.getData().get(i).isCheck_group())){
                return false;
            }
        }
        return true;
    }
}
