import http from "api/commonHttp";
import FeedBannerElement from "components/Feed/Banner/FeedBannerElement";
import FeedInput from "components/news/Banner/FeedInput";
import NewsContent from "components/news/NewsContent";
import { lightest_grey } from "lib/style/colorPalette";
import React, { useEffect, useRef, useState } from "react";
import { useParams } from "react-router-dom";
import { Background } from "styled";
import styled from "styled-components";

export default function NewsDetail() {
  const { articleId } = useParams();
  const [feeds, setFeeds] = useState([]);
  const [userId, setUserId] = useState(1);
  const page = useRef(0);

  const target = useRef(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (target.current) {
      observer.observe(target.current);
    }

    getFeeds();
  }, []);

  function getFeeds() {
    setLoading(true);
    console.log("page: ", page.current);
    http
      .get(`/feed/list/${articleId}/${userId}?page=${page.current}`)
      .then((data) => setFeeds((prevFeeds) => [...prevFeeds, ...data.data]))
      .catch((err) => alert(err));
    setLoading(false);
  }

  const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
      if (!entry.isIntersecting) return;
      if (loading) return;

      getFeeds();
      page.current += 1;
    });
  });

  return (
    <div style={{ display: "flex", padding: "40px 3rem", gap: "88px" }}>
      <NewsContent articleId={articleId} />
      <BannerContainer>
        <BannerInner>
          {feeds.length === 0 ? (
            <Background
              style={{
                height: "420px",
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              "해당 기사가 언급된 피드가 없습니다.새로운 피드를 등록해보세요!"
            </Background>
          ) : (
            <FeedListContainer>
              {feeds.map((data, index) => {
                return (
                  <React.Fragment key={data.feedId}>
                    <FeedBannerElement
                      data={data}
                      isLast={index + 1 === feeds.length}
                    />
                  </React.Fragment>
                );
              })}
              <div
                style={{ height: "20px", backgroundColor: "red" }}
                ref={target}
              />
            </FeedListContainer>
          )}

          <FeedInput articleId={articleId} userId={userId} />
        </BannerInner>
      </BannerContainer>
    </div>
  );
}
const BannerContainer = styled.div`
  flex: 4;
  display: flex;
`;

const BannerInner = styled.div`
  display: flex;
  flex: 1;
  flex-direction: column;
  gap: 1rem;
  position: fixed;
`;

const FeedListContainer = styled(Background)`
  height: 420px;
  display: flex;
  flex-direction: column;
  overflow-y: scroll;
  &::-webkit-scrollbar {
    display: none;
  }
`;