package com.haozj.lucene;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.haozj.entity.Blog;
import com.haozj.util.DateUtil;
import com.haozj.util.StringUtil;

/**
 * 博客索引类
 * 
 * @author Administrator
 *
 */
public class BlogIndex {

    private Directory dir;

    /**
     * 获取IndexWriter实例
     * 
     * @return
     * @throws Exception
     */
    private IndexWriter getWriter() throws Exception {
        dir = FSDirectory.open(Paths.get("E://lucene"));
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(dir, iwc);
        return writer;
    }

    /**
     * 添加博客索引
     * 
     * @param blog
     * @throws Exception
     */
    public void addIndex(Blog blog) throws Exception {
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
        doc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
        doc.add(new StringField("releaseDate", DateUtil.formatDate(new Date(), "yyyy-MM-dd"), Field.Store.YES));
        doc.add(new TextField("content", blog.getContentNoTag(), Field.Store.YES));
        // 将文档写入索引库
        writer.addDocument(doc);
        writer.close();
    }
    //删除索引 
    public void deleteIndex(String id) throws Exception{
        IndexWriter writer = this.getWriter();
        writer.deleteDocuments(new Term("id", id));
        //强制删除
        writer.forceMergeDeletes();
        writer.commit();
        writer.close();
    }
    //更新索引
    public void updateIndex(Blog blog)throws Exception{
        IndexWriter writer=getWriter();
        Document doc=new Document();
        doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
        doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
        doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));
        doc.add(new TextField("content",blog.getContentNoTag(),Field.Store.YES));
        writer.updateDocument(new Term("id",String.valueOf(blog.getId())), doc);
        writer.close();
    }
    /**
     * 搜索符合条件的记录
     * 
     * @param q
     * @return
     * @throws Exception
     */
    public List<Blog> searchBlog(String q) throws Exception {
        dir = FSDirectory.open(Paths.get("E://lucene"));
        IndexReader indexReader = DirectoryReader.open(dir);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
        SmartChineseAnalyzer smartChineseAnalyzer = new SmartChineseAnalyzer();
        QueryParser parser = new QueryParser("title", smartChineseAnalyzer);
        Query query = parser.parse(q);
        QueryParser parser2 = new QueryParser("content", smartChineseAnalyzer);
        Query query2 = parser2.parse(q);
        booleanQuery.add(query, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
        // 查出前一百条搜索出的记录
        TopDocs topDocs = indexSearcher.search(booleanQuery.build(), 100);
        // 根据文章标题判断得分
        QueryScorer queryScorer = new QueryScorer(query);
        // 结果高亮
        Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, queryScorer);
        highlighter.setTextFragmenter(fragmenter);
        List<Blog> blogList = new LinkedList<Blog>();
        // 获取查找文档的属性数据(title content)
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            Blog blog = new Blog();
            blog.setId(Integer.parseInt(document.get("id")));
            blog.setReleaseDateStr(document.get("releaseDate"));
            String title = document.get("title");
            //String content = StringEscapeUtils.escapeHtml(document.get("content"));
            String content = document.get("content");
            if (title != null) {
                TokenStream tokenStream = smartChineseAnalyzer.tokenStream("title", new StringReader(title));
                String highTitle = highlighter.getBestFragment(tokenStream, title);
                if (StringUtil.isNotEmpty(highTitle)) {
                    blog.setTitle(highTitle);
                } else {
                    blog.setTitle(title);
                }
            }
            if (content != null) {
                TokenStream tokenStream = smartChineseAnalyzer.tokenStream("content", new StringReader(content));
                String highContent = highlighter.getBestFragment(tokenStream, content);
                if (StringUtil.isNotEmpty(highContent)) {
                    blog.setContent(highContent);
                } else {
                    if (content.length() < 200) {
                        blog.setContent(content);
                    } else {
                        blog.setContent(content.substring(0, 200));
                    }
                }
            }
            blogList.add(blog);
        }
        return blogList;

    }
    
    

}
