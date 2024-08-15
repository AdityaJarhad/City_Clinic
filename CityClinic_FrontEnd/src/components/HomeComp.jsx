import React from 'react';
import BannerComp from './BannerComp';

import PromoBanner from './PromoBanner';
import CategoryBanner from './CategoryBanner';
const HomeComp = () => {
  return (
    <div>
      <BannerComp />
      
      <PromoBanner />
      <CategoryBanner />
    </div>
  );
};

export default HomeComp;
